import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosProgramadosElementosFluxoControlado } from '../ons-dados-programados-elementos-fluxo-controlado.model';
import { OnsDadosProgramadosElementosFluxoControladoService } from '../service/ons-dados-programados-elementos-fluxo-controlado.service';
import {
  OnsDadosProgramadosElementosFluxoControladoFormGroup,
  OnsDadosProgramadosElementosFluxoControladoFormService,
} from './ons-dados-programados-elementos-fluxo-controlado-form.service';

@Component({
  selector: 'jhi-ons-dados-programados-elementos-fluxo-controlado-update',
  templateUrl: './ons-dados-programados-elementos-fluxo-controlado-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosProgramadosElementosFluxoControladoUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado | null = null;

  protected onsDadosProgramadosElementosFluxoControladoService = inject(OnsDadosProgramadosElementosFluxoControladoService);
  protected onsDadosProgramadosElementosFluxoControladoFormService = inject(OnsDadosProgramadosElementosFluxoControladoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosProgramadosElementosFluxoControladoFormGroup =
    this.onsDadosProgramadosElementosFluxoControladoFormService.createOnsDadosProgramadosElementosFluxoControladoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosProgramadosElementosFluxoControlado }) => {
      this.onsDadosProgramadosElementosFluxoControlado = onsDadosProgramadosElementosFluxoControlado;
      if (onsDadosProgramadosElementosFluxoControlado) {
        this.updateForm(onsDadosProgramadosElementosFluxoControlado);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosProgramadosElementosFluxoControlado =
      this.onsDadosProgramadosElementosFluxoControladoFormService.getOnsDadosProgramadosElementosFluxoControlado(this.editForm);
    if (onsDadosProgramadosElementosFluxoControlado.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosProgramadosElementosFluxoControladoService.update(onsDadosProgramadosElementosFluxoControlado),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosProgramadosElementosFluxoControladoService.create(onsDadosProgramadosElementosFluxoControlado),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>>): void {
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

  protected updateForm(onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado): void {
    this.onsDadosProgramadosElementosFluxoControlado = onsDadosProgramadosElementosFluxoControlado;
    this.onsDadosProgramadosElementosFluxoControladoFormService.resetForm(this.editForm, onsDadosProgramadosElementosFluxoControlado);
  }
}
