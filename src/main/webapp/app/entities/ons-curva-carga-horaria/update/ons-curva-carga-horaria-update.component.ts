import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';
import { OnsCurvaCargaHorariaService } from '../service/ons-curva-carga-horaria.service';
import { OnsCurvaCargaHorariaFormGroup, OnsCurvaCargaHorariaFormService } from './ons-curva-carga-horaria-form.service';

@Component({
  selector: 'jhi-ons-curva-carga-horaria-update',
  templateUrl: './ons-curva-carga-horaria-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCurvaCargaHorariaUpdateComponent implements OnInit {
  isSaving = false;
  onsCurvaCargaHoraria: IOnsCurvaCargaHoraria | null = null;

  protected onsCurvaCargaHorariaService = inject(OnsCurvaCargaHorariaService);
  protected onsCurvaCargaHorariaFormService = inject(OnsCurvaCargaHorariaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCurvaCargaHorariaFormGroup = this.onsCurvaCargaHorariaFormService.createOnsCurvaCargaHorariaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCurvaCargaHoraria }) => {
      this.onsCurvaCargaHoraria = onsCurvaCargaHoraria;
      if (onsCurvaCargaHoraria) {
        this.updateForm(onsCurvaCargaHoraria);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCurvaCargaHoraria = this.onsCurvaCargaHorariaFormService.getOnsCurvaCargaHoraria(this.editForm);
    if (onsCurvaCargaHoraria.id !== null) {
      this.subscribeToSaveResponse(this.onsCurvaCargaHorariaService.update(onsCurvaCargaHoraria));
    } else {
      this.subscribeToSaveResponse(this.onsCurvaCargaHorariaService.create(onsCurvaCargaHoraria));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCurvaCargaHoraria>>): void {
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

  protected updateForm(onsCurvaCargaHoraria: IOnsCurvaCargaHoraria): void {
    this.onsCurvaCargaHoraria = onsCurvaCargaHoraria;
    this.onsCurvaCargaHorariaFormService.resetForm(this.editForm, onsCurvaCargaHoraria);
  }
}
