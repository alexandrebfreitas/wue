import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';
import { OnsIntercambiosEntreSubsistemasService } from '../service/ons-intercambios-entre-subsistemas.service';
import {
  OnsIntercambiosEntreSubsistemasFormGroup,
  OnsIntercambiosEntreSubsistemasFormService,
} from './ons-intercambios-entre-subsistemas-form.service';

@Component({
  selector: 'jhi-ons-intercambios-entre-subsistemas-update',
  templateUrl: './ons-intercambios-entre-subsistemas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIntercambiosEntreSubsistemasUpdateComponent implements OnInit {
  isSaving = false;
  onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas | null = null;

  protected onsIntercambiosEntreSubsistemasService = inject(OnsIntercambiosEntreSubsistemasService);
  protected onsIntercambiosEntreSubsistemasFormService = inject(OnsIntercambiosEntreSubsistemasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIntercambiosEntreSubsistemasFormGroup =
    this.onsIntercambiosEntreSubsistemasFormService.createOnsIntercambiosEntreSubsistemasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIntercambiosEntreSubsistemas }) => {
      this.onsIntercambiosEntreSubsistemas = onsIntercambiosEntreSubsistemas;
      if (onsIntercambiosEntreSubsistemas) {
        this.updateForm(onsIntercambiosEntreSubsistemas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIntercambiosEntreSubsistemas = this.onsIntercambiosEntreSubsistemasFormService.getOnsIntercambiosEntreSubsistemas(
      this.editForm,
    );
    if (onsIntercambiosEntreSubsistemas.id !== null) {
      this.subscribeToSaveResponse(this.onsIntercambiosEntreSubsistemasService.update(onsIntercambiosEntreSubsistemas));
    } else {
      this.subscribeToSaveResponse(this.onsIntercambiosEntreSubsistemasService.create(onsIntercambiosEntreSubsistemas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsIntercambiosEntreSubsistemas>>): void {
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

  protected updateForm(onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas): void {
    this.onsIntercambiosEntreSubsistemas = onsIntercambiosEntreSubsistemas;
    this.onsIntercambiosEntreSubsistemasFormService.resetForm(this.editForm, onsIntercambiosEntreSubsistemas);
  }
}
