import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';
import { OnsCapacidadeInstaladaGeracaoService } from '../service/ons-capacidade-instalada-geracao.service';
import {
  OnsCapacidadeInstaladaGeracaoFormGroup,
  OnsCapacidadeInstaladaGeracaoFormService,
} from './ons-capacidade-instalada-geracao-form.service';

@Component({
  selector: 'jhi-ons-capacidade-instalada-geracao-update',
  templateUrl: './ons-capacidade-instalada-geracao-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCapacidadeInstaladaGeracaoUpdateComponent implements OnInit {
  isSaving = false;
  onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao | null = null;

  protected onsCapacidadeInstaladaGeracaoService = inject(OnsCapacidadeInstaladaGeracaoService);
  protected onsCapacidadeInstaladaGeracaoFormService = inject(OnsCapacidadeInstaladaGeracaoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCapacidadeInstaladaGeracaoFormGroup =
    this.onsCapacidadeInstaladaGeracaoFormService.createOnsCapacidadeInstaladaGeracaoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCapacidadeInstaladaGeracao }) => {
      this.onsCapacidadeInstaladaGeracao = onsCapacidadeInstaladaGeracao;
      if (onsCapacidadeInstaladaGeracao) {
        this.updateForm(onsCapacidadeInstaladaGeracao);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCapacidadeInstaladaGeracao = this.onsCapacidadeInstaladaGeracaoFormService.getOnsCapacidadeInstaladaGeracao(this.editForm);
    if (onsCapacidadeInstaladaGeracao.id !== null) {
      this.subscribeToSaveResponse(this.onsCapacidadeInstaladaGeracaoService.update(onsCapacidadeInstaladaGeracao));
    } else {
      this.subscribeToSaveResponse(this.onsCapacidadeInstaladaGeracaoService.create(onsCapacidadeInstaladaGeracao));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCapacidadeInstaladaGeracao>>): void {
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

  protected updateForm(onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao): void {
    this.onsCapacidadeInstaladaGeracao = onsCapacidadeInstaladaGeracao;
    this.onsCapacidadeInstaladaGeracaoFormService.resetForm(this.editForm, onsCapacidadeInstaladaGeracao);
  }
}
