import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';
import {
  OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup,
  OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService,
} from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-form.service';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-update',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent implements OnInit {
  isSaving = false;
  onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes | null =
    null;

  protected onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService,
  );
  protected onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup =
    this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes }) => {
      this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes;
      if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes) {
        this.updateForm(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
      this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
        this.editForm,
      );
    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.id !== null) {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService.update(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService.create(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>>,
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
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  ): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
      onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes;
    this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService.resetForm(
      this.editForm,
      onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
    );
  }
}
