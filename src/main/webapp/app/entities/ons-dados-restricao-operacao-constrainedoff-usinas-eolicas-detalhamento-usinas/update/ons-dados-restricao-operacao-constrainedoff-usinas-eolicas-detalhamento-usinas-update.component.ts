import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.service';
import {
  OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup,
  OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-form.service';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-update',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas | null =
    null;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService,
  );
  protected onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup =
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas }) => {
      this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas;
      if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas) {
        this.updateForm(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
      this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(
        this.editForm,
      );
    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService.update(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService.create(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>>,
  ): void {
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
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  ): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas;
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService.resetForm(
      this.editForm,
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
    );
  }
}
