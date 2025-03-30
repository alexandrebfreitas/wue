import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';
import {
  OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup,
  OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-form.service';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-update',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas | null = null;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService,
  );
  protected onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup =
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas }) => {
      this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas;
      if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas) {
        this.updateForm(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
      this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(
        this.editForm,
      );
    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService.update(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService.create(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>>): void {
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
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  ): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas;
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService.resetForm(
      this.editForm,
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
    );
  }
}
