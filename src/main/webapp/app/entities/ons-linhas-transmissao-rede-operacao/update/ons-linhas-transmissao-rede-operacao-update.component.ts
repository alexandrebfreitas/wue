import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';
import { OnsLinhasTransmissaoRedeOperacaoService } from '../service/ons-linhas-transmissao-rede-operacao.service';
import {
  OnsLinhasTransmissaoRedeOperacaoFormGroup,
  OnsLinhasTransmissaoRedeOperacaoFormService,
} from './ons-linhas-transmissao-rede-operacao-form.service';

@Component({
  selector: 'jhi-ons-linhas-transmissao-rede-operacao-update',
  templateUrl: './ons-linhas-transmissao-rede-operacao-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsLinhasTransmissaoRedeOperacaoUpdateComponent implements OnInit {
  isSaving = false;
  onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao | null = null;

  protected onsLinhasTransmissaoRedeOperacaoService = inject(OnsLinhasTransmissaoRedeOperacaoService);
  protected onsLinhasTransmissaoRedeOperacaoFormService = inject(OnsLinhasTransmissaoRedeOperacaoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsLinhasTransmissaoRedeOperacaoFormGroup =
    this.onsLinhasTransmissaoRedeOperacaoFormService.createOnsLinhasTransmissaoRedeOperacaoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsLinhasTransmissaoRedeOperacao }) => {
      this.onsLinhasTransmissaoRedeOperacao = onsLinhasTransmissaoRedeOperacao;
      if (onsLinhasTransmissaoRedeOperacao) {
        this.updateForm(onsLinhasTransmissaoRedeOperacao);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsLinhasTransmissaoRedeOperacao = this.onsLinhasTransmissaoRedeOperacaoFormService.getOnsLinhasTransmissaoRedeOperacao(
      this.editForm,
    );
    if (onsLinhasTransmissaoRedeOperacao.id !== null) {
      this.subscribeToSaveResponse(this.onsLinhasTransmissaoRedeOperacaoService.update(onsLinhasTransmissaoRedeOperacao));
    } else {
      this.subscribeToSaveResponse(this.onsLinhasTransmissaoRedeOperacaoService.create(onsLinhasTransmissaoRedeOperacao));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsLinhasTransmissaoRedeOperacao>>): void {
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

  protected updateForm(onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao): void {
    this.onsLinhasTransmissaoRedeOperacao = onsLinhasTransmissaoRedeOperacao;
    this.onsLinhasTransmissaoRedeOperacaoFormService.resetForm(this.editForm, onsLinhasTransmissaoRedeOperacao);
  }
}
