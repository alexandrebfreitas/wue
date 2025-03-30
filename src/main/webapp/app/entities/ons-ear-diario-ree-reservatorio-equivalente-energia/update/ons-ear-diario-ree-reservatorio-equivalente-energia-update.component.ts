import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEarDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ear-diario-ree-reservatorio-equivalente-energia.service';
import {
  OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup,
  OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService,
} from './ons-ear-diario-ree-reservatorio-equivalente-energia-form.service';

@Component({
  selector: 'jhi-ons-ear-diario-ree-reservatorio-equivalente-energia-update',
  templateUrl: './ons-ear-diario-ree-reservatorio-equivalente-energia-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent implements OnInit {
  isSaving = false;
  onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia | null = null;

  protected onsEarDiarioReeReservatorioEquivalenteEnergiaService = inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaService);
  protected onsEarDiarioReeReservatorioEquivalenteEnergiaFormService = inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup =
    this.onsEarDiarioReeReservatorioEquivalenteEnergiaFormService.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEarDiarioReeReservatorioEquivalenteEnergia }) => {
      this.onsEarDiarioReeReservatorioEquivalenteEnergia = onsEarDiarioReeReservatorioEquivalenteEnergia;
      if (onsEarDiarioReeReservatorioEquivalenteEnergia) {
        this.updateForm(onsEarDiarioReeReservatorioEquivalenteEnergia);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEarDiarioReeReservatorioEquivalenteEnergia =
      this.onsEarDiarioReeReservatorioEquivalenteEnergiaFormService.getOnsEarDiarioReeReservatorioEquivalenteEnergia(this.editForm);
    if (onsEarDiarioReeReservatorioEquivalenteEnergia.id !== null) {
      this.subscribeToSaveResponse(
        this.onsEarDiarioReeReservatorioEquivalenteEnergiaService.update(onsEarDiarioReeReservatorioEquivalenteEnergia),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsEarDiarioReeReservatorioEquivalenteEnergiaService.create(onsEarDiarioReeReservatorioEquivalenteEnergia),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>>): void {
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

  protected updateForm(onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia): void {
    this.onsEarDiarioReeReservatorioEquivalenteEnergia = onsEarDiarioReeReservatorioEquivalenteEnergia;
    this.onsEarDiarioReeReservatorioEquivalenteEnergiaFormService.resetForm(this.editForm, onsEarDiarioReeReservatorioEquivalenteEnergia);
  }
}
