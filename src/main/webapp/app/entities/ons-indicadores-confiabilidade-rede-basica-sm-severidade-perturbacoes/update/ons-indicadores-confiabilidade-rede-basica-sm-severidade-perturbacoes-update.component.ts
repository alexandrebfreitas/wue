import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';
import {
  OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup,
  OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService,
} from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-form.service';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-update',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent implements OnInit {
  isSaving = false;
  onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes | null =
    null;

  protected onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService,
  );
  protected onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup =
    this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes }) => {
      this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;
      if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes) {
        this.updateForm(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
      this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(
        this.editForm,
      );
    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.id !== null) {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.update(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.create(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>>,
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
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  ): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;
    this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService.resetForm(
      this.editForm,
      onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
    );
  }
}
