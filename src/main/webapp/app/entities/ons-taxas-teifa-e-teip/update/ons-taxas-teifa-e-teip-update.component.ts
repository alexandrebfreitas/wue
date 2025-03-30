import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';
import { OnsTaxasTeifaETeipService } from '../service/ons-taxas-teifa-e-teip.service';
import { OnsTaxasTeifaETeipFormGroup, OnsTaxasTeifaETeipFormService } from './ons-taxas-teifa-e-teip-form.service';

@Component({
  selector: 'jhi-ons-taxas-teifa-e-teip-update',
  templateUrl: './ons-taxas-teifa-e-teip-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsTaxasTeifaETeipUpdateComponent implements OnInit {
  isSaving = false;
  onsTaxasTeifaETeip: IOnsTaxasTeifaETeip | null = null;

  protected onsTaxasTeifaETeipService = inject(OnsTaxasTeifaETeipService);
  protected onsTaxasTeifaETeipFormService = inject(OnsTaxasTeifaETeipFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsTaxasTeifaETeipFormGroup = this.onsTaxasTeifaETeipFormService.createOnsTaxasTeifaETeipFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsTaxasTeifaETeip }) => {
      this.onsTaxasTeifaETeip = onsTaxasTeifaETeip;
      if (onsTaxasTeifaETeip) {
        this.updateForm(onsTaxasTeifaETeip);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsTaxasTeifaETeip = this.onsTaxasTeifaETeipFormService.getOnsTaxasTeifaETeip(this.editForm);
    if (onsTaxasTeifaETeip.id !== null) {
      this.subscribeToSaveResponse(this.onsTaxasTeifaETeipService.update(onsTaxasTeifaETeip));
    } else {
      this.subscribeToSaveResponse(this.onsTaxasTeifaETeipService.create(onsTaxasTeifaETeip));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsTaxasTeifaETeip>>): void {
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

  protected updateForm(onsTaxasTeifaETeip: IOnsTaxasTeifaETeip): void {
    this.onsTaxasTeifaETeip = onsTaxasTeifaETeip;
    this.onsTaxasTeifaETeipFormService.resetForm(this.editForm, onsTaxasTeifaETeip);
  }
}
