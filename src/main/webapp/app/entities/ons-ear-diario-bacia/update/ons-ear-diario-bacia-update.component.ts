import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';
import { OnsEarDiarioBaciaService } from '../service/ons-ear-diario-bacia.service';
import { OnsEarDiarioBaciaFormGroup, OnsEarDiarioBaciaFormService } from './ons-ear-diario-bacia-form.service';

@Component({
  selector: 'jhi-ons-ear-diario-bacia-update',
  templateUrl: './ons-ear-diario-bacia-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEarDiarioBaciaUpdateComponent implements OnInit {
  isSaving = false;
  onsEarDiarioBacia: IOnsEarDiarioBacia | null = null;

  protected onsEarDiarioBaciaService = inject(OnsEarDiarioBaciaService);
  protected onsEarDiarioBaciaFormService = inject(OnsEarDiarioBaciaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEarDiarioBaciaFormGroup = this.onsEarDiarioBaciaFormService.createOnsEarDiarioBaciaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEarDiarioBacia }) => {
      this.onsEarDiarioBacia = onsEarDiarioBacia;
      if (onsEarDiarioBacia) {
        this.updateForm(onsEarDiarioBacia);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEarDiarioBacia = this.onsEarDiarioBaciaFormService.getOnsEarDiarioBacia(this.editForm);
    if (onsEarDiarioBacia.id !== null) {
      this.subscribeToSaveResponse(this.onsEarDiarioBaciaService.update(onsEarDiarioBacia));
    } else {
      this.subscribeToSaveResponse(this.onsEarDiarioBaciaService.create(onsEarDiarioBacia));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEarDiarioBacia>>): void {
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

  protected updateForm(onsEarDiarioBacia: IOnsEarDiarioBacia): void {
    this.onsEarDiarioBacia = onsEarDiarioBacia;
    this.onsEarDiarioBaciaFormService.resetForm(this.editForm, onsEarDiarioBacia);
  }
}
