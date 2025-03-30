import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';
import { OnsOfertasPrecoParaImportacaoService } from '../service/ons-ofertas-preco-para-importacao.service';
import {
  OnsOfertasPrecoParaImportacaoFormGroup,
  OnsOfertasPrecoParaImportacaoFormService,
} from './ons-ofertas-preco-para-importacao-form.service';

@Component({
  selector: 'jhi-ons-ofertas-preco-para-importacao-update',
  templateUrl: './ons-ofertas-preco-para-importacao-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsOfertasPrecoParaImportacaoUpdateComponent implements OnInit {
  isSaving = false;
  onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao | null = null;

  protected onsOfertasPrecoParaImportacaoService = inject(OnsOfertasPrecoParaImportacaoService);
  protected onsOfertasPrecoParaImportacaoFormService = inject(OnsOfertasPrecoParaImportacaoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsOfertasPrecoParaImportacaoFormGroup =
    this.onsOfertasPrecoParaImportacaoFormService.createOnsOfertasPrecoParaImportacaoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsOfertasPrecoParaImportacao }) => {
      this.onsOfertasPrecoParaImportacao = onsOfertasPrecoParaImportacao;
      if (onsOfertasPrecoParaImportacao) {
        this.updateForm(onsOfertasPrecoParaImportacao);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsOfertasPrecoParaImportacao = this.onsOfertasPrecoParaImportacaoFormService.getOnsOfertasPrecoParaImportacao(this.editForm);
    if (onsOfertasPrecoParaImportacao.id !== null) {
      this.subscribeToSaveResponse(this.onsOfertasPrecoParaImportacaoService.update(onsOfertasPrecoParaImportacao));
    } else {
      this.subscribeToSaveResponse(this.onsOfertasPrecoParaImportacaoService.create(onsOfertasPrecoParaImportacao));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsOfertasPrecoParaImportacao>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao): void {
    this.onsOfertasPrecoParaImportacao = onsOfertasPrecoParaImportacao;
    this.onsOfertasPrecoParaImportacaoFormService.resetForm(this.editForm, onsOfertasPrecoParaImportacao);
  }
}
