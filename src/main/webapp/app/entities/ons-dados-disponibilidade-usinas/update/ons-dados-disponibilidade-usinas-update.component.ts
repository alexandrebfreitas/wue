import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';
import { OnsDadosDisponibilidadeUsinasService } from '../service/ons-dados-disponibilidade-usinas.service';
import {
  OnsDadosDisponibilidadeUsinasFormGroup,
  OnsDadosDisponibilidadeUsinasFormService,
} from './ons-dados-disponibilidade-usinas-form.service';

@Component({
  selector: 'jhi-ons-dados-disponibilidade-usinas-update',
  templateUrl: './ons-dados-disponibilidade-usinas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosDisponibilidadeUsinasUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas | null = null;

  protected onsDadosDisponibilidadeUsinasService = inject(OnsDadosDisponibilidadeUsinasService);
  protected onsDadosDisponibilidadeUsinasFormService = inject(OnsDadosDisponibilidadeUsinasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosDisponibilidadeUsinasFormGroup =
    this.onsDadosDisponibilidadeUsinasFormService.createOnsDadosDisponibilidadeUsinasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosDisponibilidadeUsinas }) => {
      this.onsDadosDisponibilidadeUsinas = onsDadosDisponibilidadeUsinas;
      if (onsDadosDisponibilidadeUsinas) {
        this.updateForm(onsDadosDisponibilidadeUsinas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosDisponibilidadeUsinas = this.onsDadosDisponibilidadeUsinasFormService.getOnsDadosDisponibilidadeUsinas(this.editForm);
    if (onsDadosDisponibilidadeUsinas.id !== null) {
      this.subscribeToSaveResponse(this.onsDadosDisponibilidadeUsinasService.update(onsDadosDisponibilidadeUsinas));
    } else {
      this.subscribeToSaveResponse(this.onsDadosDisponibilidadeUsinasService.create(onsDadosDisponibilidadeUsinas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosDisponibilidadeUsinas>>): void {
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

  protected updateForm(onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas): void {
    this.onsDadosDisponibilidadeUsinas = onsDadosDisponibilidadeUsinas;
    this.onsDadosDisponibilidadeUsinasFormService.resetForm(this.editForm, onsDadosDisponibilidadeUsinas);
  }
}
