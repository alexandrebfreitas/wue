import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';
import { OnsDadosIntercambioEnergiaModalidadeService } from '../service/ons-dados-intercambio-energia-modalidade.service';
import {
  OnsDadosIntercambioEnergiaModalidadeFormGroup,
  OnsDadosIntercambioEnergiaModalidadeFormService,
} from './ons-dados-intercambio-energia-modalidade-form.service';

@Component({
  selector: 'jhi-ons-dados-intercambio-energia-modalidade-update',
  templateUrl: './ons-dados-intercambio-energia-modalidade-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosIntercambioEnergiaModalidadeUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade | null = null;

  protected onsDadosIntercambioEnergiaModalidadeService = inject(OnsDadosIntercambioEnergiaModalidadeService);
  protected onsDadosIntercambioEnergiaModalidadeFormService = inject(OnsDadosIntercambioEnergiaModalidadeFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosIntercambioEnergiaModalidadeFormGroup =
    this.onsDadosIntercambioEnergiaModalidadeFormService.createOnsDadosIntercambioEnergiaModalidadeFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosIntercambioEnergiaModalidade }) => {
      this.onsDadosIntercambioEnergiaModalidade = onsDadosIntercambioEnergiaModalidade;
      if (onsDadosIntercambioEnergiaModalidade) {
        this.updateForm(onsDadosIntercambioEnergiaModalidade);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosIntercambioEnergiaModalidade =
      this.onsDadosIntercambioEnergiaModalidadeFormService.getOnsDadosIntercambioEnergiaModalidade(this.editForm);
    if (onsDadosIntercambioEnergiaModalidade.id !== null) {
      this.subscribeToSaveResponse(this.onsDadosIntercambioEnergiaModalidadeService.update(onsDadosIntercambioEnergiaModalidade));
    } else {
      this.subscribeToSaveResponse(this.onsDadosIntercambioEnergiaModalidadeService.create(onsDadosIntercambioEnergiaModalidade));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosIntercambioEnergiaModalidade>>): void {
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

  protected updateForm(onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade): void {
    this.onsDadosIntercambioEnergiaModalidade = onsDadosIntercambioEnergiaModalidade;
    this.onsDadosIntercambioEnergiaModalidadeFormService.resetForm(this.editForm, onsDadosIntercambioEnergiaModalidade);
  }
}
