import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';
import { OnsEnaDiarioBaciaService } from '../service/ons-ena-diario-bacia.service';
import { OnsEnaDiarioBaciaFormGroup, OnsEnaDiarioBaciaFormService } from './ons-ena-diario-bacia-form.service';

@Component({
  selector: 'jhi-ons-ena-diario-bacia-update',
  templateUrl: './ons-ena-diario-bacia-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEnaDiarioBaciaUpdateComponent implements OnInit {
  isSaving = false;
  onsEnaDiarioBacia: IOnsEnaDiarioBacia | null = null;

  protected onsEnaDiarioBaciaService = inject(OnsEnaDiarioBaciaService);
  protected onsEnaDiarioBaciaFormService = inject(OnsEnaDiarioBaciaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEnaDiarioBaciaFormGroup = this.onsEnaDiarioBaciaFormService.createOnsEnaDiarioBaciaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEnaDiarioBacia }) => {
      this.onsEnaDiarioBacia = onsEnaDiarioBacia;
      if (onsEnaDiarioBacia) {
        this.updateForm(onsEnaDiarioBacia);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEnaDiarioBacia = this.onsEnaDiarioBaciaFormService.getOnsEnaDiarioBacia(this.editForm);
    if (onsEnaDiarioBacia.id !== null) {
      this.subscribeToSaveResponse(this.onsEnaDiarioBaciaService.update(onsEnaDiarioBacia));
    } else {
      this.subscribeToSaveResponse(this.onsEnaDiarioBaciaService.create(onsEnaDiarioBacia));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEnaDiarioBacia>>): void {
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

  protected updateForm(onsEnaDiarioBacia: IOnsEnaDiarioBacia): void {
    this.onsEnaDiarioBacia = onsEnaDiarioBacia;
    this.onsEnaDiarioBaciaFormService.resetForm(this.editForm, onsEnaDiarioBacia);
  }
}
