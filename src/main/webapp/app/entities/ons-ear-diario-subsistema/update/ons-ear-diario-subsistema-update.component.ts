import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';
import { OnsEarDiarioSubsistemaService } from '../service/ons-ear-diario-subsistema.service';
import { OnsEarDiarioSubsistemaFormGroup, OnsEarDiarioSubsistemaFormService } from './ons-ear-diario-subsistema-form.service';

@Component({
  selector: 'jhi-ons-ear-diario-subsistema-update',
  templateUrl: './ons-ear-diario-subsistema-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEarDiarioSubsistemaUpdateComponent implements OnInit {
  isSaving = false;
  onsEarDiarioSubsistema: IOnsEarDiarioSubsistema | null = null;

  protected onsEarDiarioSubsistemaService = inject(OnsEarDiarioSubsistemaService);
  protected onsEarDiarioSubsistemaFormService = inject(OnsEarDiarioSubsistemaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEarDiarioSubsistemaFormGroup = this.onsEarDiarioSubsistemaFormService.createOnsEarDiarioSubsistemaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEarDiarioSubsistema }) => {
      this.onsEarDiarioSubsistema = onsEarDiarioSubsistema;
      if (onsEarDiarioSubsistema) {
        this.updateForm(onsEarDiarioSubsistema);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEarDiarioSubsistema = this.onsEarDiarioSubsistemaFormService.getOnsEarDiarioSubsistema(this.editForm);
    if (onsEarDiarioSubsistema.id !== null) {
      this.subscribeToSaveResponse(this.onsEarDiarioSubsistemaService.update(onsEarDiarioSubsistema));
    } else {
      this.subscribeToSaveResponse(this.onsEarDiarioSubsistemaService.create(onsEarDiarioSubsistema));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEarDiarioSubsistema>>): void {
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

  protected updateForm(onsEarDiarioSubsistema: IOnsEarDiarioSubsistema): void {
    this.onsEarDiarioSubsistema = onsEarDiarioSubsistema;
    this.onsEarDiarioSubsistemaFormService.resetForm(this.editForm, onsEarDiarioSubsistema);
  }
}
