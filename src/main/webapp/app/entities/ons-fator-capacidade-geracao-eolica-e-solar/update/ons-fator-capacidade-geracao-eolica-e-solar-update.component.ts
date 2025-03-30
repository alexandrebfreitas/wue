import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';
import { OnsFatorCapacidadeGeracaoEolicaESolarService } from '../service/ons-fator-capacidade-geracao-eolica-e-solar.service';
import {
  OnsFatorCapacidadeGeracaoEolicaESolarFormGroup,
  OnsFatorCapacidadeGeracaoEolicaESolarFormService,
} from './ons-fator-capacidade-geracao-eolica-e-solar-form.service';

@Component({
  selector: 'jhi-ons-fator-capacidade-geracao-eolica-e-solar-update',
  templateUrl: './ons-fator-capacidade-geracao-eolica-e-solar-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent implements OnInit {
  isSaving = false;
  onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar | null = null;

  protected onsFatorCapacidadeGeracaoEolicaESolarService = inject(OnsFatorCapacidadeGeracaoEolicaESolarService);
  protected onsFatorCapacidadeGeracaoEolicaESolarFormService = inject(OnsFatorCapacidadeGeracaoEolicaESolarFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsFatorCapacidadeGeracaoEolicaESolarFormGroup =
    this.onsFatorCapacidadeGeracaoEolicaESolarFormService.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsFatorCapacidadeGeracaoEolicaESolar }) => {
      this.onsFatorCapacidadeGeracaoEolicaESolar = onsFatorCapacidadeGeracaoEolicaESolar;
      if (onsFatorCapacidadeGeracaoEolicaESolar) {
        this.updateForm(onsFatorCapacidadeGeracaoEolicaESolar);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsFatorCapacidadeGeracaoEolicaESolar =
      this.onsFatorCapacidadeGeracaoEolicaESolarFormService.getOnsFatorCapacidadeGeracaoEolicaESolar(this.editForm);
    if (onsFatorCapacidadeGeracaoEolicaESolar.id !== null) {
      this.subscribeToSaveResponse(this.onsFatorCapacidadeGeracaoEolicaESolarService.update(onsFatorCapacidadeGeracaoEolicaESolar));
    } else {
      this.subscribeToSaveResponse(this.onsFatorCapacidadeGeracaoEolicaESolarService.create(onsFatorCapacidadeGeracaoEolicaESolar));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>>): void {
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

  protected updateForm(onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar): void {
    this.onsFatorCapacidadeGeracaoEolicaESolar = onsFatorCapacidadeGeracaoEolicaESolar;
    this.onsFatorCapacidadeGeracaoEolicaESolarFormService.resetForm(this.editForm, onsFatorCapacidadeGeracaoEolicaESolar);
  }
}
