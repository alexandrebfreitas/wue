import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';
import { OnsCargaEnergiaDiariaService } from '../service/ons-carga-energia-diaria.service';
import { OnsCargaEnergiaDiariaFormGroup, OnsCargaEnergiaDiariaFormService } from './ons-carga-energia-diaria-form.service';

@Component({
  selector: 'jhi-ons-carga-energia-diaria-update',
  templateUrl: './ons-carga-energia-diaria-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCargaEnergiaDiariaUpdateComponent implements OnInit {
  isSaving = false;
  onsCargaEnergiaDiaria: IOnsCargaEnergiaDiaria | null = null;

  protected onsCargaEnergiaDiariaService = inject(OnsCargaEnergiaDiariaService);
  protected onsCargaEnergiaDiariaFormService = inject(OnsCargaEnergiaDiariaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCargaEnergiaDiariaFormGroup = this.onsCargaEnergiaDiariaFormService.createOnsCargaEnergiaDiariaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCargaEnergiaDiaria }) => {
      this.onsCargaEnergiaDiaria = onsCargaEnergiaDiaria;
      if (onsCargaEnergiaDiaria) {
        this.updateForm(onsCargaEnergiaDiaria);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCargaEnergiaDiaria = this.onsCargaEnergiaDiariaFormService.getOnsCargaEnergiaDiaria(this.editForm);
    if (onsCargaEnergiaDiaria.id !== null) {
      this.subscribeToSaveResponse(this.onsCargaEnergiaDiariaService.update(onsCargaEnergiaDiaria));
    } else {
      this.subscribeToSaveResponse(this.onsCargaEnergiaDiariaService.create(onsCargaEnergiaDiaria));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCargaEnergiaDiaria>>): void {
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

  protected updateForm(onsCargaEnergiaDiaria: IOnsCargaEnergiaDiaria): void {
    this.onsCargaEnergiaDiaria = onsCargaEnergiaDiaria;
    this.onsCargaEnergiaDiariaFormService.resetForm(this.editForm, onsCargaEnergiaDiaria);
  }
}
