import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';
import { OnsGeracaoComercialParaExportacaoInternacionalService } from '../service/ons-geracao-comercial-para-exportacao-internacional.service';
import {
  OnsGeracaoComercialParaExportacaoInternacionalFormGroup,
  OnsGeracaoComercialParaExportacaoInternacionalFormService,
} from './ons-geracao-comercial-para-exportacao-internacional-form.service';

@Component({
  selector: 'jhi-ons-geracao-comercial-para-exportacao-internacional-update',
  templateUrl: './ons-geracao-comercial-para-exportacao-internacional-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent implements OnInit {
  isSaving = false;
  onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional | null = null;

  protected onsGeracaoComercialParaExportacaoInternacionalService = inject(OnsGeracaoComercialParaExportacaoInternacionalService);
  protected onsGeracaoComercialParaExportacaoInternacionalFormService = inject(OnsGeracaoComercialParaExportacaoInternacionalFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsGeracaoComercialParaExportacaoInternacionalFormGroup =
    this.onsGeracaoComercialParaExportacaoInternacionalFormService.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsGeracaoComercialParaExportacaoInternacional }) => {
      this.onsGeracaoComercialParaExportacaoInternacional = onsGeracaoComercialParaExportacaoInternacional;
      if (onsGeracaoComercialParaExportacaoInternacional) {
        this.updateForm(onsGeracaoComercialParaExportacaoInternacional);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsGeracaoComercialParaExportacaoInternacional =
      this.onsGeracaoComercialParaExportacaoInternacionalFormService.getOnsGeracaoComercialParaExportacaoInternacional(this.editForm);
    if (onsGeracaoComercialParaExportacaoInternacional.id !== null) {
      this.subscribeToSaveResponse(
        this.onsGeracaoComercialParaExportacaoInternacionalService.update(onsGeracaoComercialParaExportacaoInternacional),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsGeracaoComercialParaExportacaoInternacionalService.create(onsGeracaoComercialParaExportacaoInternacional),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>>): void {
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

  protected updateForm(onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional): void {
    this.onsGeracaoComercialParaExportacaoInternacional = onsGeracaoComercialParaExportacaoInternacional;
    this.onsGeracaoComercialParaExportacaoInternacionalFormService.resetForm(this.editForm, onsGeracaoComercialParaExportacaoInternacional);
  }
}
