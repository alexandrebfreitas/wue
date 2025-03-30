import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';
import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService } from '../service/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';
import {
  OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup,
  OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService,
} from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-form.service';

@Component({
  selector: 'jhi-ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-update',
  templateUrl: './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa | null = null;

  protected onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService = inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService);
  protected onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService = inject(
    OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup =
    this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa }) => {
      this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa;
      if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa) {
        this.updateForm(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
      this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
        this.editForm,
      );
    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService.update(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService.create(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>>): void {
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

  protected updateForm(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa): void {
    this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa;
    this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService.resetForm(
      this.editForm,
      onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
    );
  }
}
