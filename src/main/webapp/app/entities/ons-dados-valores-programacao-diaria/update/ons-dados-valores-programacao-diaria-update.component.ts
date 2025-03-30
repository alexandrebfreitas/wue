import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import { OnsDadosValoresProgramacaoDiariaService } from '../service/ons-dados-valores-programacao-diaria.service';
import {
  OnsDadosValoresProgramacaoDiariaFormGroup,
  OnsDadosValoresProgramacaoDiariaFormService,
} from './ons-dados-valores-programacao-diaria-form.service';

@Component({
  selector: 'jhi-ons-dados-valores-programacao-diaria-update',
  templateUrl: './ons-dados-valores-programacao-diaria-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosValoresProgramacaoDiariaUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria | null = null;

  protected onsDadosValoresProgramacaoDiariaService = inject(OnsDadosValoresProgramacaoDiariaService);
  protected onsDadosValoresProgramacaoDiariaFormService = inject(OnsDadosValoresProgramacaoDiariaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosValoresProgramacaoDiariaFormGroup =
    this.onsDadosValoresProgramacaoDiariaFormService.createOnsDadosValoresProgramacaoDiariaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosValoresProgramacaoDiaria }) => {
      this.onsDadosValoresProgramacaoDiaria = onsDadosValoresProgramacaoDiaria;
      if (onsDadosValoresProgramacaoDiaria) {
        this.updateForm(onsDadosValoresProgramacaoDiaria);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosValoresProgramacaoDiaria = this.onsDadosValoresProgramacaoDiariaFormService.getOnsDadosValoresProgramacaoDiaria(
      this.editForm,
    );
    if (onsDadosValoresProgramacaoDiaria.id !== null) {
      this.subscribeToSaveResponse(this.onsDadosValoresProgramacaoDiariaService.update(onsDadosValoresProgramacaoDiaria));
    } else {
      this.subscribeToSaveResponse(this.onsDadosValoresProgramacaoDiariaService.create(onsDadosValoresProgramacaoDiaria));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosValoresProgramacaoDiaria>>): void {
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

  protected updateForm(onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria): void {
    this.onsDadosValoresProgramacaoDiaria = onsDadosValoresProgramacaoDiaria;
    this.onsDadosValoresProgramacaoDiariaFormService.resetForm(this.editForm, onsDadosValoresProgramacaoDiaria);
  }
}
