import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ena-diario-ree-reservatorio-equivalente-energia.service';
import {
  OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup,
  OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService,
} from './ons-ena-diario-ree-reservatorio-equivalente-energia-form.service';

@Component({
  selector: 'jhi-ons-ena-diario-ree-reservatorio-equivalente-energia-update',
  templateUrl: './ons-ena-diario-ree-reservatorio-equivalente-energia-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent implements OnInit {
  isSaving = false;
  onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia | null = null;

  protected onsEnaDiarioReeReservatorioEquivalenteEnergiaService = inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService);
  protected onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService = inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup =
    this.onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEnaDiarioReeReservatorioEquivalenteEnergia }) => {
      this.onsEnaDiarioReeReservatorioEquivalenteEnergia = onsEnaDiarioReeReservatorioEquivalenteEnergia;
      if (onsEnaDiarioReeReservatorioEquivalenteEnergia) {
        this.updateForm(onsEnaDiarioReeReservatorioEquivalenteEnergia);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEnaDiarioReeReservatorioEquivalenteEnergia =
      this.onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService.getOnsEnaDiarioReeReservatorioEquivalenteEnergia(this.editForm);
    if (onsEnaDiarioReeReservatorioEquivalenteEnergia.id !== null) {
      this.subscribeToSaveResponse(
        this.onsEnaDiarioReeReservatorioEquivalenteEnergiaService.update(onsEnaDiarioReeReservatorioEquivalenteEnergia),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsEnaDiarioReeReservatorioEquivalenteEnergiaService.create(onsEnaDiarioReeReservatorioEquivalenteEnergia),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>>): void {
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

  protected updateForm(onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia): void {
    this.onsEnaDiarioReeReservatorioEquivalenteEnergia = onsEnaDiarioReeReservatorioEquivalenteEnergia;
    this.onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService.resetForm(this.editForm, onsEnaDiarioReeReservatorioEquivalenteEnergia);
  }
}
