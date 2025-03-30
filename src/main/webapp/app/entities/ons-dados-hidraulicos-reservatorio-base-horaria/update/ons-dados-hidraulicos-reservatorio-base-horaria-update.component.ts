import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';
import { OnsDadosHidraulicosReservatorioBaseHorariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-horaria.service';
import {
  OnsDadosHidraulicosReservatorioBaseHorariaFormGroup,
  OnsDadosHidraulicosReservatorioBaseHorariaFormService,
} from './ons-dados-hidraulicos-reservatorio-base-horaria-form.service';

@Component({
  selector: 'jhi-ons-dados-hidraulicos-reservatorio-base-horaria-update',
  templateUrl: './ons-dados-hidraulicos-reservatorio-base-horaria-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria | null = null;

  protected onsDadosHidraulicosReservatorioBaseHorariaService = inject(OnsDadosHidraulicosReservatorioBaseHorariaService);
  protected onsDadosHidraulicosReservatorioBaseHorariaFormService = inject(OnsDadosHidraulicosReservatorioBaseHorariaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosHidraulicosReservatorioBaseHorariaFormGroup =
    this.onsDadosHidraulicosReservatorioBaseHorariaFormService.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosHidraulicosReservatorioBaseHoraria }) => {
      this.onsDadosHidraulicosReservatorioBaseHoraria = onsDadosHidraulicosReservatorioBaseHoraria;
      if (onsDadosHidraulicosReservatorioBaseHoraria) {
        this.updateForm(onsDadosHidraulicosReservatorioBaseHoraria);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosHidraulicosReservatorioBaseHoraria =
      this.onsDadosHidraulicosReservatorioBaseHorariaFormService.getOnsDadosHidraulicosReservatorioBaseHoraria(this.editForm);
    if (onsDadosHidraulicosReservatorioBaseHoraria.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosHidraulicosReservatorioBaseHorariaService.update(onsDadosHidraulicosReservatorioBaseHoraria),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosHidraulicosReservatorioBaseHorariaService.create(onsDadosHidraulicosReservatorioBaseHoraria),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>>): void {
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

  protected updateForm(onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria): void {
    this.onsDadosHidraulicosReservatorioBaseHoraria = onsDadosHidraulicosReservatorioBaseHoraria;
    this.onsDadosHidraulicosReservatorioBaseHorariaFormService.resetForm(this.editForm, onsDadosHidraulicosReservatorioBaseHoraria);
  }
}
