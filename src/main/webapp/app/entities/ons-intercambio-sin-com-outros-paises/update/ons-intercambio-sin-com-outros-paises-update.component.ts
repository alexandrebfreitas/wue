import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';
import { OnsIntercambioSinComOutrosPaisesService } from '../service/ons-intercambio-sin-com-outros-paises.service';
import {
  OnsIntercambioSinComOutrosPaisesFormGroup,
  OnsIntercambioSinComOutrosPaisesFormService,
} from './ons-intercambio-sin-com-outros-paises-form.service';

@Component({
  selector: 'jhi-ons-intercambio-sin-com-outros-paises-update',
  templateUrl: './ons-intercambio-sin-com-outros-paises-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIntercambioSinComOutrosPaisesUpdateComponent implements OnInit {
  isSaving = false;
  onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises | null = null;

  protected onsIntercambioSinComOutrosPaisesService = inject(OnsIntercambioSinComOutrosPaisesService);
  protected onsIntercambioSinComOutrosPaisesFormService = inject(OnsIntercambioSinComOutrosPaisesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIntercambioSinComOutrosPaisesFormGroup =
    this.onsIntercambioSinComOutrosPaisesFormService.createOnsIntercambioSinComOutrosPaisesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIntercambioSinComOutrosPaises }) => {
      this.onsIntercambioSinComOutrosPaises = onsIntercambioSinComOutrosPaises;
      if (onsIntercambioSinComOutrosPaises) {
        this.updateForm(onsIntercambioSinComOutrosPaises);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIntercambioSinComOutrosPaises = this.onsIntercambioSinComOutrosPaisesFormService.getOnsIntercambioSinComOutrosPaises(
      this.editForm,
    );
    if (onsIntercambioSinComOutrosPaises.id !== null) {
      this.subscribeToSaveResponse(this.onsIntercambioSinComOutrosPaisesService.update(onsIntercambioSinComOutrosPaises));
    } else {
      this.subscribeToSaveResponse(this.onsIntercambioSinComOutrosPaisesService.create(onsIntercambioSinComOutrosPaises));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsIntercambioSinComOutrosPaises>>): void {
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

  protected updateForm(onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises): void {
    this.onsIntercambioSinComOutrosPaises = onsIntercambioSinComOutrosPaises;
    this.onsIntercambioSinComOutrosPaisesFormService.resetForm(this.editForm, onsIntercambioSinComOutrosPaises);
  }
}
