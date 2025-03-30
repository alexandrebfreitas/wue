import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import { OnsEnaDiarioSubsistemaService } from '../service/ons-ena-diario-subsistema.service';
import { OnsEnaDiarioSubsistemaFormGroup, OnsEnaDiarioSubsistemaFormService } from './ons-ena-diario-subsistema-form.service';

@Component({
  selector: 'jhi-ons-ena-diario-subsistema-update',
  templateUrl: './ons-ena-diario-subsistema-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEnaDiarioSubsistemaUpdateComponent implements OnInit {
  isSaving = false;
  onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema | null = null;

  protected onsEnaDiarioSubsistemaService = inject(OnsEnaDiarioSubsistemaService);
  protected onsEnaDiarioSubsistemaFormService = inject(OnsEnaDiarioSubsistemaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEnaDiarioSubsistemaFormGroup = this.onsEnaDiarioSubsistemaFormService.createOnsEnaDiarioSubsistemaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEnaDiarioSubsistema }) => {
      this.onsEnaDiarioSubsistema = onsEnaDiarioSubsistema;
      if (onsEnaDiarioSubsistema) {
        this.updateForm(onsEnaDiarioSubsistema);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEnaDiarioSubsistema = this.onsEnaDiarioSubsistemaFormService.getOnsEnaDiarioSubsistema(this.editForm);
    if (onsEnaDiarioSubsistema.id !== null) {
      this.subscribeToSaveResponse(this.onsEnaDiarioSubsistemaService.update(onsEnaDiarioSubsistema));
    } else {
      this.subscribeToSaveResponse(this.onsEnaDiarioSubsistemaService.create(onsEnaDiarioSubsistema));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEnaDiarioSubsistema>>): void {
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

  protected updateForm(onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema): void {
    this.onsEnaDiarioSubsistema = onsEnaDiarioSubsistema;
    this.onsEnaDiarioSubsistemaFormService.resetForm(this.editForm, onsEnaDiarioSubsistema);
  }
}
