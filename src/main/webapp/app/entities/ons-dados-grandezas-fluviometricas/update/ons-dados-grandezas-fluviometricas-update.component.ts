import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';
import { OnsDadosGrandezasFluviometricasService } from '../service/ons-dados-grandezas-fluviometricas.service';
import {
  OnsDadosGrandezasFluviometricasFormGroup,
  OnsDadosGrandezasFluviometricasFormService,
} from './ons-dados-grandezas-fluviometricas-form.service';

@Component({
  selector: 'jhi-ons-dados-grandezas-fluviometricas-update',
  templateUrl: './ons-dados-grandezas-fluviometricas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosGrandezasFluviometricasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas | null = null;

  protected onsDadosGrandezasFluviometricasService = inject(OnsDadosGrandezasFluviometricasService);
  protected onsDadosGrandezasFluviometricasFormService = inject(OnsDadosGrandezasFluviometricasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosGrandezasFluviometricasFormGroup =
    this.onsDadosGrandezasFluviometricasFormService.createOnsDadosGrandezasFluviometricasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosGrandezasFluviometricas }) => {
      this.onsDadosGrandezasFluviometricas = onsDadosGrandezasFluviometricas;
      if (onsDadosGrandezasFluviometricas) {
        this.updateForm(onsDadosGrandezasFluviometricas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosGrandezasFluviometricas = this.onsDadosGrandezasFluviometricasFormService.getOnsDadosGrandezasFluviometricas(
      this.editForm,
    );
    if (onsDadosGrandezasFluviometricas.id !== null) {
      this.subscribeToSaveResponse(this.onsDadosGrandezasFluviometricasService.update(onsDadosGrandezasFluviometricas));
    } else {
      this.subscribeToSaveResponse(this.onsDadosGrandezasFluviometricasService.create(onsDadosGrandezasFluviometricas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosGrandezasFluviometricas>>): void {
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

  protected updateForm(onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas): void {
    this.onsDadosGrandezasFluviometricas = onsDadosGrandezasFluviometricas;
    this.onsDadosGrandezasFluviometricasFormService.resetForm(this.editForm, onsDadosGrandezasFluviometricas);
  }
}
