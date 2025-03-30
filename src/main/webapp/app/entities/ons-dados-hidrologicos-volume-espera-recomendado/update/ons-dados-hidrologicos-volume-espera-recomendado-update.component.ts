import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';
import { OnsDadosHidrologicosVolumeEsperaRecomendadoService } from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';
import {
  OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup,
  OnsDadosHidrologicosVolumeEsperaRecomendadoFormService,
} from './ons-dados-hidrologicos-volume-espera-recomendado-form.service';

@Component({
  selector: 'jhi-ons-dados-hidrologicos-volume-espera-recomendado-update',
  templateUrl: './ons-dados-hidrologicos-volume-espera-recomendado-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosHidrologicosVolumeEsperaRecomendado: IOnsDadosHidrologicosVolumeEsperaRecomendado | null = null;

  protected onsDadosHidrologicosVolumeEsperaRecomendadoService = inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService);
  protected onsDadosHidrologicosVolumeEsperaRecomendadoFormService = inject(OnsDadosHidrologicosVolumeEsperaRecomendadoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup =
    this.onsDadosHidrologicosVolumeEsperaRecomendadoFormService.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosHidrologicosVolumeEsperaRecomendado }) => {
      this.onsDadosHidrologicosVolumeEsperaRecomendado = onsDadosHidrologicosVolumeEsperaRecomendado;
      if (onsDadosHidrologicosVolumeEsperaRecomendado) {
        this.updateForm(onsDadosHidrologicosVolumeEsperaRecomendado);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosHidrologicosVolumeEsperaRecomendado =
      this.onsDadosHidrologicosVolumeEsperaRecomendadoFormService.getOnsDadosHidrologicosVolumeEsperaRecomendado(this.editForm);
    if (onsDadosHidrologicosVolumeEsperaRecomendado.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosHidrologicosVolumeEsperaRecomendadoService.update(onsDadosHidrologicosVolumeEsperaRecomendado),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosHidrologicosVolumeEsperaRecomendadoService.create(onsDadosHidrologicosVolumeEsperaRecomendado),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>>): void {
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

  protected updateForm(onsDadosHidrologicosVolumeEsperaRecomendado: IOnsDadosHidrologicosVolumeEsperaRecomendado): void {
    this.onsDadosHidrologicosVolumeEsperaRecomendado = onsDadosHidrologicosVolumeEsperaRecomendado;
    this.onsDadosHidrologicosVolumeEsperaRecomendadoFormService.resetForm(this.editForm, onsDadosHidrologicosVolumeEsperaRecomendado);
  }
}
