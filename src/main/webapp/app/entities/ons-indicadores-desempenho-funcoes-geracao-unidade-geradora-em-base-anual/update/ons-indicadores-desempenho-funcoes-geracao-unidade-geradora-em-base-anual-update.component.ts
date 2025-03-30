import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';
import {
  OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup,
  OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService,
} from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-form.service';

@Component({
  selector: 'jhi-ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-update',
  templateUrl: './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent implements OnInit {
  isSaving = false;
  onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual | null =
    null;

  protected onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService = inject(
    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService,
  );
  protected onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService = inject(
    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup =
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual }) => {
      this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual;
      if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual) {
        this.updateForm(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
      this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(
        this.editForm,
      );
    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.id !== null) {
      this.subscribeToSaveResponse(
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService.update(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService.create(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>>,
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
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  ): void {
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual;
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService.resetForm(
      this.editForm,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
    );
  }
}
