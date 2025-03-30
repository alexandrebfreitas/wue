import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.service';
import {
  OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup,
  OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-form.service';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-update',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas | null = null;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService,
  );
  protected onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup =
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas }) => {
      this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas;
      if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas) {
        this.updateForm(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
      this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
        this.editForm,
      );
    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService.update(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService.create(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>>): void {
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

  protected updateForm(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas;
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService.resetForm(
      this.editForm,
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
    );
  }
}
