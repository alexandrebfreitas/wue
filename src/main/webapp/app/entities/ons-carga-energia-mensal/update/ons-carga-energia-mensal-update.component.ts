import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';
import { OnsCargaEnergiaMensalService } from '../service/ons-carga-energia-mensal.service';
import { OnsCargaEnergiaMensalFormGroup, OnsCargaEnergiaMensalFormService } from './ons-carga-energia-mensal-form.service';

@Component({
  selector: 'jhi-ons-carga-energia-mensal-update',
  templateUrl: './ons-carga-energia-mensal-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCargaEnergiaMensalUpdateComponent implements OnInit {
  isSaving = false;
  onsCargaEnergiaMensal: IOnsCargaEnergiaMensal | null = null;

  protected onsCargaEnergiaMensalService = inject(OnsCargaEnergiaMensalService);
  protected onsCargaEnergiaMensalFormService = inject(OnsCargaEnergiaMensalFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCargaEnergiaMensalFormGroup = this.onsCargaEnergiaMensalFormService.createOnsCargaEnergiaMensalFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCargaEnergiaMensal }) => {
      this.onsCargaEnergiaMensal = onsCargaEnergiaMensal;
      if (onsCargaEnergiaMensal) {
        this.updateForm(onsCargaEnergiaMensal);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCargaEnergiaMensal = this.onsCargaEnergiaMensalFormService.getOnsCargaEnergiaMensal(this.editForm);
    if (onsCargaEnergiaMensal.id !== null) {
      this.subscribeToSaveResponse(this.onsCargaEnergiaMensalService.update(onsCargaEnergiaMensal));
    } else {
      this.subscribeToSaveResponse(this.onsCargaEnergiaMensalService.create(onsCargaEnergiaMensal));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCargaEnergiaMensal>>): void {
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

  protected updateForm(onsCargaEnergiaMensal: IOnsCargaEnergiaMensal): void {
    this.onsCargaEnergiaMensal = onsCargaEnergiaMensal;
    this.onsCargaEnergiaMensalFormService.resetForm(this.editForm, onsCargaEnergiaMensal);
  }
}
