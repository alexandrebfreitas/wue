import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';
import {
  OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup,
  OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService,
} from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-form.service';

@Component({
  selector: 'jhi-ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-update',
  templateUrl: './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent implements OnInit {
  isSaving = false;
  onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal | null =
    null;

  protected onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService = inject(
    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService,
  );
  protected onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService = inject(
    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup =
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal }) => {
      this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal;
      if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal) {
        this.updateForm(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
      this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(
        this.editForm,
      );
    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.id !== null) {
      this.subscribeToSaveResponse(
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService.update(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService.create(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>>,
  ): void {
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

  protected updateForm(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  ): void {
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal;
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService.resetForm(
      this.editForm,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
    );
  }
}
