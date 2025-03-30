import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import { OnsEnaDiarioReservatorioService } from '../service/ons-ena-diario-reservatorio.service';
import { OnsEnaDiarioReservatorioFormGroup, OnsEnaDiarioReservatorioFormService } from './ons-ena-diario-reservatorio-form.service';

@Component({
  selector: 'jhi-ons-ena-diario-reservatorio-update',
  templateUrl: './ons-ena-diario-reservatorio-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEnaDiarioReservatorioUpdateComponent implements OnInit {
  isSaving = false;
  onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio | null = null;

  protected onsEnaDiarioReservatorioService = inject(OnsEnaDiarioReservatorioService);
  protected onsEnaDiarioReservatorioFormService = inject(OnsEnaDiarioReservatorioFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEnaDiarioReservatorioFormGroup = this.onsEnaDiarioReservatorioFormService.createOnsEnaDiarioReservatorioFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEnaDiarioReservatorio }) => {
      this.onsEnaDiarioReservatorio = onsEnaDiarioReservatorio;
      if (onsEnaDiarioReservatorio) {
        this.updateForm(onsEnaDiarioReservatorio);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEnaDiarioReservatorio = this.onsEnaDiarioReservatorioFormService.getOnsEnaDiarioReservatorio(this.editForm);
    if (onsEnaDiarioReservatorio.id !== null) {
      this.subscribeToSaveResponse(this.onsEnaDiarioReservatorioService.update(onsEnaDiarioReservatorio));
    } else {
      this.subscribeToSaveResponse(this.onsEnaDiarioReservatorioService.create(onsEnaDiarioReservatorio));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEnaDiarioReservatorio>>): void {
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

  protected updateForm(onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio): void {
    this.onsEnaDiarioReservatorio = onsEnaDiarioReservatorio;
    this.onsEnaDiarioReservatorioFormService.resetForm(this.editForm, onsEnaDiarioReservatorio);
  }
}
