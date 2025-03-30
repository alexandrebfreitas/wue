import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';
import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService } from '../service/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';
import {
  OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup,
  OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService,
} from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-form.service';

@Component({
  selector: 'jhi-ons-dados-valores-previsao-versus-programado-eolicas-e-solares-update',
  templateUrl: './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares | null = null;

  protected onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService = inject(
    OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService,
  );
  protected onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService = inject(
    OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup =
    this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosValoresPrevisaoVersusProgramadoEolicasESolares }) => {
      this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = onsDadosValoresPrevisaoVersusProgramadoEolicasESolares;
      if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolares) {
        this.updateForm(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
      this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
        this.editForm,
      );
    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService.update(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService.create(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>>): void {
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

  protected updateForm(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  ): void {
    this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = onsDadosValoresPrevisaoVersusProgramadoEolicasESolares;
    this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService.resetForm(
      this.editForm,
      onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
    );
  }
}
