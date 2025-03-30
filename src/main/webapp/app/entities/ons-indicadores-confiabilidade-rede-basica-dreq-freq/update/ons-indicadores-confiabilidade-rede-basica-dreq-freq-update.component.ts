import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';
import {
  OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup,
  OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService,
} from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-form.service';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-dreq-freq-update',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-dreq-freq-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent implements OnInit {
  isSaving = false;
  onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq | null = null;

  protected onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService = inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);
  protected onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService = inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup =
    this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIndicadoresConfiabilidadeRedeBasicaDreqFreq }) => {
      this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = onsIndicadoresConfiabilidadeRedeBasicaDreqFreq;
      if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreq) {
        this.updateForm(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq =
      this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(this.editForm);
    if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.id !== null) {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.update(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.create(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>>): void {
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

  protected updateForm(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = onsIndicadoresConfiabilidadeRedeBasicaDreqFreq;
    this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService.resetForm(this.editForm, onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
  }
}
