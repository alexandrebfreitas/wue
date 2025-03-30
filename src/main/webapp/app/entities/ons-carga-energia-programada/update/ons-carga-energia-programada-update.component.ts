import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';
import { OnsCargaEnergiaProgramadaService } from '../service/ons-carga-energia-programada.service';
import { OnsCargaEnergiaProgramadaFormGroup, OnsCargaEnergiaProgramadaFormService } from './ons-carga-energia-programada-form.service';

@Component({
  selector: 'jhi-ons-carga-energia-programada-update',
  templateUrl: './ons-carga-energia-programada-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCargaEnergiaProgramadaUpdateComponent implements OnInit {
  isSaving = false;
  onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada | null = null;

  protected onsCargaEnergiaProgramadaService = inject(OnsCargaEnergiaProgramadaService);
  protected onsCargaEnergiaProgramadaFormService = inject(OnsCargaEnergiaProgramadaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCargaEnergiaProgramadaFormGroup = this.onsCargaEnergiaProgramadaFormService.createOnsCargaEnergiaProgramadaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCargaEnergiaProgramada }) => {
      this.onsCargaEnergiaProgramada = onsCargaEnergiaProgramada;
      if (onsCargaEnergiaProgramada) {
        this.updateForm(onsCargaEnergiaProgramada);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCargaEnergiaProgramada = this.onsCargaEnergiaProgramadaFormService.getOnsCargaEnergiaProgramada(this.editForm);
    if (onsCargaEnergiaProgramada.id !== null) {
      this.subscribeToSaveResponse(this.onsCargaEnergiaProgramadaService.update(onsCargaEnergiaProgramada));
    } else {
      this.subscribeToSaveResponse(this.onsCargaEnergiaProgramadaService.create(onsCargaEnergiaProgramada));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCargaEnergiaProgramada>>): void {
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

  protected updateForm(onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada): void {
    this.onsCargaEnergiaProgramada = onsCargaEnergiaProgramada;
    this.onsCargaEnergiaProgramadaFormService.resetForm(this.editForm, onsCargaEnergiaProgramada);
  }
}
