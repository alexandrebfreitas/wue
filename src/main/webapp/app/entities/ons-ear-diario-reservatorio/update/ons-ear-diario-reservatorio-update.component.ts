import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';
import { OnsEarDiarioReservatorioService } from '../service/ons-ear-diario-reservatorio.service';
import { OnsEarDiarioReservatorioFormGroup, OnsEarDiarioReservatorioFormService } from './ons-ear-diario-reservatorio-form.service';

@Component({
  selector: 'jhi-ons-ear-diario-reservatorio-update',
  templateUrl: './ons-ear-diario-reservatorio-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEarDiarioReservatorioUpdateComponent implements OnInit {
  isSaving = false;
  onsEarDiarioReservatorio: IOnsEarDiarioReservatorio | null = null;

  protected onsEarDiarioReservatorioService = inject(OnsEarDiarioReservatorioService);
  protected onsEarDiarioReservatorioFormService = inject(OnsEarDiarioReservatorioFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEarDiarioReservatorioFormGroup = this.onsEarDiarioReservatorioFormService.createOnsEarDiarioReservatorioFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEarDiarioReservatorio }) => {
      this.onsEarDiarioReservatorio = onsEarDiarioReservatorio;
      if (onsEarDiarioReservatorio) {
        this.updateForm(onsEarDiarioReservatorio);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEarDiarioReservatorio = this.onsEarDiarioReservatorioFormService.getOnsEarDiarioReservatorio(this.editForm);
    if (onsEarDiarioReservatorio.id !== null) {
      this.subscribeToSaveResponse(this.onsEarDiarioReservatorioService.update(onsEarDiarioReservatorio));
    } else {
      this.subscribeToSaveResponse(this.onsEarDiarioReservatorioService.create(onsEarDiarioReservatorio));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEarDiarioReservatorio>>): void {
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

  protected updateForm(onsEarDiarioReservatorio: IOnsEarDiarioReservatorio): void {
    this.onsEarDiarioReservatorio = onsEarDiarioReservatorio;
    this.onsEarDiarioReservatorioFormService.resetForm(this.editForm, onsEarDiarioReservatorio);
  }
}
