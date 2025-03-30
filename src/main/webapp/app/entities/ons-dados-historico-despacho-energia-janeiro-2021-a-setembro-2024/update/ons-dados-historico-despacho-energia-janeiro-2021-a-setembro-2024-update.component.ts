import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';
import {
  OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup,
  OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService,
} from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-form.service';

@Component({
  selector: 'jhi-ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-update',
  templateUrl: './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent implements OnInit {
  isSaving = false;
  onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 | null = null;

  protected onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service = inject(
    OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service,
  );
  protected onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService = inject(
    OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup =
    this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 }) => {
      this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;
      if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024) {
        this.updateForm(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
      this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(
        this.editForm,
      );
    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.update(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.create(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>>): void {
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
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  ): void {
    this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;
    this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService.resetForm(
      this.editForm,
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
    );
  }
}
