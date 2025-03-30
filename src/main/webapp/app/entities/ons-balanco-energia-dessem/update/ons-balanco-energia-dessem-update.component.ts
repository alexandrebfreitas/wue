import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';
import { OnsBalancoEnergiaDessemService } from '../service/ons-balanco-energia-dessem.service';
import { OnsBalancoEnergiaDessemFormGroup, OnsBalancoEnergiaDessemFormService } from './ons-balanco-energia-dessem-form.service';

@Component({
  selector: 'jhi-ons-balanco-energia-dessem-update',
  templateUrl: './ons-balanco-energia-dessem-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsBalancoEnergiaDessemUpdateComponent implements OnInit {
  isSaving = false;
  onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem | null = null;

  protected onsBalancoEnergiaDessemService = inject(OnsBalancoEnergiaDessemService);
  protected onsBalancoEnergiaDessemFormService = inject(OnsBalancoEnergiaDessemFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsBalancoEnergiaDessemFormGroup = this.onsBalancoEnergiaDessemFormService.createOnsBalancoEnergiaDessemFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsBalancoEnergiaDessem }) => {
      this.onsBalancoEnergiaDessem = onsBalancoEnergiaDessem;
      if (onsBalancoEnergiaDessem) {
        this.updateForm(onsBalancoEnergiaDessem);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsBalancoEnergiaDessem = this.onsBalancoEnergiaDessemFormService.getOnsBalancoEnergiaDessem(this.editForm);
    if (onsBalancoEnergiaDessem.id !== null) {
      this.subscribeToSaveResponse(this.onsBalancoEnergiaDessemService.update(onsBalancoEnergiaDessem));
    } else {
      this.subscribeToSaveResponse(this.onsBalancoEnergiaDessemService.create(onsBalancoEnergiaDessem));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsBalancoEnergiaDessem>>): void {
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

  protected updateForm(onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem): void {
    this.onsBalancoEnergiaDessem = onsBalancoEnergiaDessem;
    this.onsBalancoEnergiaDessemFormService.resetForm(this.editForm, onsBalancoEnergiaDessem);
  }
}
