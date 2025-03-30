import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosHidraulicosReservatorioBaseDiaria } from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';
import { OnsDadosHidraulicosReservatorioBaseDiariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-diaria.service';
import {
  OnsDadosHidraulicosReservatorioBaseDiariaFormGroup,
  OnsDadosHidraulicosReservatorioBaseDiariaFormService,
} from './ons-dados-hidraulicos-reservatorio-base-diaria-form.service';

@Component({
  selector: 'jhi-ons-dados-hidraulicos-reservatorio-base-diaria-update',
  templateUrl: './ons-dados-hidraulicos-reservatorio-base-diaria-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria | null = null;

  protected onsDadosHidraulicosReservatorioBaseDiariaService = inject(OnsDadosHidraulicosReservatorioBaseDiariaService);
  protected onsDadosHidraulicosReservatorioBaseDiariaFormService = inject(OnsDadosHidraulicosReservatorioBaseDiariaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosHidraulicosReservatorioBaseDiariaFormGroup =
    this.onsDadosHidraulicosReservatorioBaseDiariaFormService.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosHidraulicosReservatorioBaseDiaria }) => {
      this.onsDadosHidraulicosReservatorioBaseDiaria = onsDadosHidraulicosReservatorioBaseDiaria;
      if (onsDadosHidraulicosReservatorioBaseDiaria) {
        this.updateForm(onsDadosHidraulicosReservatorioBaseDiaria);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosHidraulicosReservatorioBaseDiaria =
      this.onsDadosHidraulicosReservatorioBaseDiariaFormService.getOnsDadosHidraulicosReservatorioBaseDiaria(this.editForm);
    if (onsDadosHidraulicosReservatorioBaseDiaria.id !== null) {
      this.subscribeToSaveResponse(this.onsDadosHidraulicosReservatorioBaseDiariaService.update(onsDadosHidraulicosReservatorioBaseDiaria));
    } else {
      this.subscribeToSaveResponse(this.onsDadosHidraulicosReservatorioBaseDiariaService.create(onsDadosHidraulicosReservatorioBaseDiaria));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria>>): void {
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

  protected updateForm(onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria): void {
    this.onsDadosHidraulicosReservatorioBaseDiaria = onsDadosHidraulicosReservatorioBaseDiaria;
    this.onsDadosHidraulicosReservatorioBaseDiariaFormService.resetForm(this.editForm, onsDadosHidraulicosReservatorioBaseDiaria);
  }
}
