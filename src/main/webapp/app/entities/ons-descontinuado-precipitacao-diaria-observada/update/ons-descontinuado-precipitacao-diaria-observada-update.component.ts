import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDescontinuadoPrecipitacaoDiariaObservada } from '../ons-descontinuado-precipitacao-diaria-observada.model';
import { OnsDescontinuadoPrecipitacaoDiariaObservadaService } from '../service/ons-descontinuado-precipitacao-diaria-observada.service';
import {
  OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup,
  OnsDescontinuadoPrecipitacaoDiariaObservadaFormService,
} from './ons-descontinuado-precipitacao-diaria-observada-form.service';

@Component({
  selector: 'jhi-ons-descontinuado-precipitacao-diaria-observada-update',
  templateUrl: './ons-descontinuado-precipitacao-diaria-observada-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent implements OnInit {
  isSaving = false;
  onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada | null = null;

  protected onsDescontinuadoPrecipitacaoDiariaObservadaService = inject(OnsDescontinuadoPrecipitacaoDiariaObservadaService);
  protected onsDescontinuadoPrecipitacaoDiariaObservadaFormService = inject(OnsDescontinuadoPrecipitacaoDiariaObservadaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup =
    this.onsDescontinuadoPrecipitacaoDiariaObservadaFormService.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDescontinuadoPrecipitacaoDiariaObservada }) => {
      this.onsDescontinuadoPrecipitacaoDiariaObservada = onsDescontinuadoPrecipitacaoDiariaObservada;
      if (onsDescontinuadoPrecipitacaoDiariaObservada) {
        this.updateForm(onsDescontinuadoPrecipitacaoDiariaObservada);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDescontinuadoPrecipitacaoDiariaObservada =
      this.onsDescontinuadoPrecipitacaoDiariaObservadaFormService.getOnsDescontinuadoPrecipitacaoDiariaObservada(this.editForm);
    if (onsDescontinuadoPrecipitacaoDiariaObservada.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDescontinuadoPrecipitacaoDiariaObservadaService.update(onsDescontinuadoPrecipitacaoDiariaObservada),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDescontinuadoPrecipitacaoDiariaObservadaService.create(onsDescontinuadoPrecipitacaoDiariaObservada),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>>): void {
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

  protected updateForm(onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada): void {
    this.onsDescontinuadoPrecipitacaoDiariaObservada = onsDescontinuadoPrecipitacaoDiariaObservada;
    this.onsDescontinuadoPrecipitacaoDiariaObservadaFormService.resetForm(this.editForm, onsDescontinuadoPrecipitacaoDiariaObservada);
  }
}
