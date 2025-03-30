import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';
import { OnsCargaEnergiaVerificadaService } from '../service/ons-carga-energia-verificada.service';
import { OnsCargaEnergiaVerificadaFormGroup, OnsCargaEnergiaVerificadaFormService } from './ons-carga-energia-verificada-form.service';

@Component({
  selector: 'jhi-ons-carga-energia-verificada-update',
  templateUrl: './ons-carga-energia-verificada-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCargaEnergiaVerificadaUpdateComponent implements OnInit {
  isSaving = false;
  onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada | null = null;

  protected onsCargaEnergiaVerificadaService = inject(OnsCargaEnergiaVerificadaService);
  protected onsCargaEnergiaVerificadaFormService = inject(OnsCargaEnergiaVerificadaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCargaEnergiaVerificadaFormGroup = this.onsCargaEnergiaVerificadaFormService.createOnsCargaEnergiaVerificadaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCargaEnergiaVerificada }) => {
      this.onsCargaEnergiaVerificada = onsCargaEnergiaVerificada;
      if (onsCargaEnergiaVerificada) {
        this.updateForm(onsCargaEnergiaVerificada);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCargaEnergiaVerificada = this.onsCargaEnergiaVerificadaFormService.getOnsCargaEnergiaVerificada(this.editForm);
    if (onsCargaEnergiaVerificada.id !== null) {
      this.subscribeToSaveResponse(this.onsCargaEnergiaVerificadaService.update(onsCargaEnergiaVerificada));
    } else {
      this.subscribeToSaveResponse(this.onsCargaEnergiaVerificadaService.create(onsCargaEnergiaVerificada));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCargaEnergiaVerificada>>): void {
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

  protected updateForm(onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada): void {
    this.onsCargaEnergiaVerificada = onsCargaEnergiaVerificada;
    this.onsCargaEnergiaVerificadaFormService.resetForm(this.editForm, onsCargaEnergiaVerificada);
  }
}
