import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';
import { OnsBalancoEnergiaNosSubsistemasService } from '../service/ons-balanco-energia-nos-subsistemas.service';
import {
  OnsBalancoEnergiaNosSubsistemasFormGroup,
  OnsBalancoEnergiaNosSubsistemasFormService,
} from './ons-balanco-energia-nos-subsistemas-form.service';

@Component({
  selector: 'jhi-ons-balanco-energia-nos-subsistemas-update',
  templateUrl: './ons-balanco-energia-nos-subsistemas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsBalancoEnergiaNosSubsistemasUpdateComponent implements OnInit {
  isSaving = false;
  onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas | null = null;

  protected onsBalancoEnergiaNosSubsistemasService = inject(OnsBalancoEnergiaNosSubsistemasService);
  protected onsBalancoEnergiaNosSubsistemasFormService = inject(OnsBalancoEnergiaNosSubsistemasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsBalancoEnergiaNosSubsistemasFormGroup =
    this.onsBalancoEnergiaNosSubsistemasFormService.createOnsBalancoEnergiaNosSubsistemasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsBalancoEnergiaNosSubsistemas }) => {
      this.onsBalancoEnergiaNosSubsistemas = onsBalancoEnergiaNosSubsistemas;
      if (onsBalancoEnergiaNosSubsistemas) {
        this.updateForm(onsBalancoEnergiaNosSubsistemas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsBalancoEnergiaNosSubsistemas = this.onsBalancoEnergiaNosSubsistemasFormService.getOnsBalancoEnergiaNosSubsistemas(
      this.editForm,
    );
    if (onsBalancoEnergiaNosSubsistemas.id !== null) {
      this.subscribeToSaveResponse(this.onsBalancoEnergiaNosSubsistemasService.update(onsBalancoEnergiaNosSubsistemas));
    } else {
      this.subscribeToSaveResponse(this.onsBalancoEnergiaNosSubsistemasService.create(onsBalancoEnergiaNosSubsistemas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsBalancoEnergiaNosSubsistemas>>): void {
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

  protected updateForm(onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas): void {
    this.onsBalancoEnergiaNosSubsistemas = onsBalancoEnergiaNosSubsistemas;
    this.onsBalancoEnergiaNosSubsistemasFormService.resetForm(this.editForm, onsBalancoEnergiaNosSubsistemas);
  }
}
