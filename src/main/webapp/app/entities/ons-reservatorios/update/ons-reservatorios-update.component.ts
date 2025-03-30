import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsReservatorios } from '../ons-reservatorios.model';
import { OnsReservatoriosService } from '../service/ons-reservatorios.service';
import { OnsReservatoriosFormGroup, OnsReservatoriosFormService } from './ons-reservatorios-form.service';

@Component({
  selector: 'jhi-ons-reservatorios-update',
  templateUrl: './ons-reservatorios-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsReservatoriosUpdateComponent implements OnInit {
  isSaving = false;
  onsReservatorios: IOnsReservatorios | null = null;

  protected onsReservatoriosService = inject(OnsReservatoriosService);
  protected onsReservatoriosFormService = inject(OnsReservatoriosFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsReservatoriosFormGroup = this.onsReservatoriosFormService.createOnsReservatoriosFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsReservatorios }) => {
      this.onsReservatorios = onsReservatorios;
      if (onsReservatorios) {
        this.updateForm(onsReservatorios);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsReservatorios = this.onsReservatoriosFormService.getOnsReservatorios(this.editForm);
    if (onsReservatorios.id !== null) {
      this.subscribeToSaveResponse(this.onsReservatoriosService.update(onsReservatorios));
    } else {
      this.subscribeToSaveResponse(this.onsReservatoriosService.create(onsReservatorios));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsReservatorios>>): void {
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

  protected updateForm(onsReservatorios: IOnsReservatorios): void {
    this.onsReservatorios = onsReservatorios;
    this.onsReservatoriosFormService.resetForm(this.editForm, onsReservatorios);
  }
}
