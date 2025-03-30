import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';
import { OnsDadosRelacionamentoEntreConjuntosEUsinasService } from '../service/ons-dados-relacionamento-entre-conjuntos-e-usinas.service';
import {
  OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup,
  OnsDadosRelacionamentoEntreConjuntosEUsinasFormService,
} from './ons-dados-relacionamento-entre-conjuntos-e-usinas-form.service';

@Component({
  selector: 'jhi-ons-dados-relacionamento-entre-conjuntos-e-usinas-update',
  templateUrl: './ons-dados-relacionamento-entre-conjuntos-e-usinas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas | null = null;

  protected onsDadosRelacionamentoEntreConjuntosEUsinasService = inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService);
  protected onsDadosRelacionamentoEntreConjuntosEUsinasFormService = inject(OnsDadosRelacionamentoEntreConjuntosEUsinasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup =
    this.onsDadosRelacionamentoEntreConjuntosEUsinasFormService.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosRelacionamentoEntreConjuntosEUsinas }) => {
      this.onsDadosRelacionamentoEntreConjuntosEUsinas = onsDadosRelacionamentoEntreConjuntosEUsinas;
      if (onsDadosRelacionamentoEntreConjuntosEUsinas) {
        this.updateForm(onsDadosRelacionamentoEntreConjuntosEUsinas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosRelacionamentoEntreConjuntosEUsinas =
      this.onsDadosRelacionamentoEntreConjuntosEUsinasFormService.getOnsDadosRelacionamentoEntreConjuntosEUsinas(this.editForm);
    if (onsDadosRelacionamentoEntreConjuntosEUsinas.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosRelacionamentoEntreConjuntosEUsinasService.update(onsDadosRelacionamentoEntreConjuntosEUsinas),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosRelacionamentoEntreConjuntosEUsinasService.create(onsDadosRelacionamentoEntreConjuntosEUsinas),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>>): void {
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

  protected updateForm(onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas): void {
    this.onsDadosRelacionamentoEntreConjuntosEUsinas = onsDadosRelacionamentoEntreConjuntosEUsinas;
    this.onsDadosRelacionamentoEntreConjuntosEUsinasFormService.resetForm(this.editForm, onsDadosRelacionamentoEntreConjuntosEUsinas);
  }
}
