import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';
import { OnsGeracaoUsinaEmBaseHorariaService } from '../service/ons-geracao-usina-em-base-horaria.service';
import {
  OnsGeracaoUsinaEmBaseHorariaFormGroup,
  OnsGeracaoUsinaEmBaseHorariaFormService,
} from './ons-geracao-usina-em-base-horaria-form.service';

@Component({
  selector: 'jhi-ons-geracao-usina-em-base-horaria-update',
  templateUrl: './ons-geracao-usina-em-base-horaria-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsGeracaoUsinaEmBaseHorariaUpdateComponent implements OnInit {
  isSaving = false;
  onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria | null = null;

  protected onsGeracaoUsinaEmBaseHorariaService = inject(OnsGeracaoUsinaEmBaseHorariaService);
  protected onsGeracaoUsinaEmBaseHorariaFormService = inject(OnsGeracaoUsinaEmBaseHorariaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsGeracaoUsinaEmBaseHorariaFormGroup =
    this.onsGeracaoUsinaEmBaseHorariaFormService.createOnsGeracaoUsinaEmBaseHorariaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsGeracaoUsinaEmBaseHoraria }) => {
      this.onsGeracaoUsinaEmBaseHoraria = onsGeracaoUsinaEmBaseHoraria;
      if (onsGeracaoUsinaEmBaseHoraria) {
        this.updateForm(onsGeracaoUsinaEmBaseHoraria);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsGeracaoUsinaEmBaseHoraria = this.onsGeracaoUsinaEmBaseHorariaFormService.getOnsGeracaoUsinaEmBaseHoraria(this.editForm);
    if (onsGeracaoUsinaEmBaseHoraria.id !== null) {
      this.subscribeToSaveResponse(this.onsGeracaoUsinaEmBaseHorariaService.update(onsGeracaoUsinaEmBaseHoraria));
    } else {
      this.subscribeToSaveResponse(this.onsGeracaoUsinaEmBaseHorariaService.create(onsGeracaoUsinaEmBaseHoraria));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsGeracaoUsinaEmBaseHoraria>>): void {
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

  protected updateForm(onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria): void {
    this.onsGeracaoUsinaEmBaseHoraria = onsGeracaoUsinaEmBaseHoraria;
    this.onsGeracaoUsinaEmBaseHorariaFormService.resetForm(this.editForm, onsGeracaoUsinaEmBaseHoraria);
  }
}
