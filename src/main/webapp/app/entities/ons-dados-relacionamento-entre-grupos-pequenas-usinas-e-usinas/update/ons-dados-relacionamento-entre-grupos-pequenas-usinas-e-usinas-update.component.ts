import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';
import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService } from '../service/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.service';
import {
  OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup,
  OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService,
} from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-form.service';

@Component({
  selector: 'jhi-ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-update',
  templateUrl: './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas | null = null;

  protected onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService = inject(
    OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService,
  );
  protected onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService = inject(
    OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup =
    this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas }) => {
      this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas;
      if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas) {
        this.updateForm(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
      this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
        this.editForm,
      );
    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService.update(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService.create(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>>): void {
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
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  ): void {
    this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas;
    this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService.resetForm(
      this.editForm,
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
    );
  }
}
