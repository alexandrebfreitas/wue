import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';
import { OnsGeracaoTermicaMotivoDespachoService } from '../service/ons-geracao-termica-motivo-despacho.service';
import {
  OnsGeracaoTermicaMotivoDespachoFormGroup,
  OnsGeracaoTermicaMotivoDespachoFormService,
} from './ons-geracao-termica-motivo-despacho-form.service';

@Component({
  selector: 'jhi-ons-geracao-termica-motivo-despacho-update',
  templateUrl: './ons-geracao-termica-motivo-despacho-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsGeracaoTermicaMotivoDespachoUpdateComponent implements OnInit {
  isSaving = false;
  onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho | null = null;

  protected onsGeracaoTermicaMotivoDespachoService = inject(OnsGeracaoTermicaMotivoDespachoService);
  protected onsGeracaoTermicaMotivoDespachoFormService = inject(OnsGeracaoTermicaMotivoDespachoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsGeracaoTermicaMotivoDespachoFormGroup =
    this.onsGeracaoTermicaMotivoDespachoFormService.createOnsGeracaoTermicaMotivoDespachoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsGeracaoTermicaMotivoDespacho }) => {
      this.onsGeracaoTermicaMotivoDespacho = onsGeracaoTermicaMotivoDespacho;
      if (onsGeracaoTermicaMotivoDespacho) {
        this.updateForm(onsGeracaoTermicaMotivoDespacho);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsGeracaoTermicaMotivoDespacho = this.onsGeracaoTermicaMotivoDespachoFormService.getOnsGeracaoTermicaMotivoDespacho(
      this.editForm,
    );
    if (onsGeracaoTermicaMotivoDespacho.id !== null) {
      this.subscribeToSaveResponse(this.onsGeracaoTermicaMotivoDespachoService.update(onsGeracaoTermicaMotivoDespacho));
    } else {
      this.subscribeToSaveResponse(this.onsGeracaoTermicaMotivoDespachoService.create(onsGeracaoTermicaMotivoDespacho));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsGeracaoTermicaMotivoDespacho>>): void {
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

  protected updateForm(onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho): void {
    this.onsGeracaoTermicaMotivoDespacho = onsGeracaoTermicaMotivoDespacho;
    this.onsGeracaoTermicaMotivoDespachoFormService.resetForm(this.editForm, onsGeracaoTermicaMotivoDespacho);
  }
}
