import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';
import { OnsCmoSemanalService } from '../service/ons-cmo-semanal.service';
import { OnsCmoSemanalFormGroup, OnsCmoSemanalFormService } from './ons-cmo-semanal-form.service';

@Component({
  selector: 'jhi-ons-cmo-semanal-update',
  templateUrl: './ons-cmo-semanal-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCmoSemanalUpdateComponent implements OnInit {
  isSaving = false;
  onsCmoSemanal: IOnsCmoSemanal | null = null;

  protected onsCmoSemanalService = inject(OnsCmoSemanalService);
  protected onsCmoSemanalFormService = inject(OnsCmoSemanalFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCmoSemanalFormGroup = this.onsCmoSemanalFormService.createOnsCmoSemanalFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCmoSemanal }) => {
      this.onsCmoSemanal = onsCmoSemanal;
      if (onsCmoSemanal) {
        this.updateForm(onsCmoSemanal);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCmoSemanal = this.onsCmoSemanalFormService.getOnsCmoSemanal(this.editForm);
    if (onsCmoSemanal.id !== null) {
      this.subscribeToSaveResponse(this.onsCmoSemanalService.update(onsCmoSemanal));
    } else {
      this.subscribeToSaveResponse(this.onsCmoSemanalService.create(onsCmoSemanal));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCmoSemanal>>): void {
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

  protected updateForm(onsCmoSemanal: IOnsCmoSemanal): void {
    this.onsCmoSemanal = onsCmoSemanal;
    this.onsCmoSemanalFormService.resetForm(this.editForm, onsCmoSemanal);
  }
}
