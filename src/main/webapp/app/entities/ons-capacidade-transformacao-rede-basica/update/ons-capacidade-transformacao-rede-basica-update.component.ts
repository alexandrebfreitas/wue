import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';
import { OnsCapacidadeTransformacaoRedeBasicaService } from '../service/ons-capacidade-transformacao-rede-basica.service';
import {
  OnsCapacidadeTransformacaoRedeBasicaFormGroup,
  OnsCapacidadeTransformacaoRedeBasicaFormService,
} from './ons-capacidade-transformacao-rede-basica-form.service';

@Component({
  selector: 'jhi-ons-capacidade-transformacao-rede-basica-update',
  templateUrl: './ons-capacidade-transformacao-rede-basica-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCapacidadeTransformacaoRedeBasicaUpdateComponent implements OnInit {
  isSaving = false;
  onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica | null = null;

  protected onsCapacidadeTransformacaoRedeBasicaService = inject(OnsCapacidadeTransformacaoRedeBasicaService);
  protected onsCapacidadeTransformacaoRedeBasicaFormService = inject(OnsCapacidadeTransformacaoRedeBasicaFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCapacidadeTransformacaoRedeBasicaFormGroup =
    this.onsCapacidadeTransformacaoRedeBasicaFormService.createOnsCapacidadeTransformacaoRedeBasicaFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCapacidadeTransformacaoRedeBasica }) => {
      this.onsCapacidadeTransformacaoRedeBasica = onsCapacidadeTransformacaoRedeBasica;
      if (onsCapacidadeTransformacaoRedeBasica) {
        this.updateForm(onsCapacidadeTransformacaoRedeBasica);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCapacidadeTransformacaoRedeBasica =
      this.onsCapacidadeTransformacaoRedeBasicaFormService.getOnsCapacidadeTransformacaoRedeBasica(this.editForm);
    if (onsCapacidadeTransformacaoRedeBasica.id !== null) {
      this.subscribeToSaveResponse(this.onsCapacidadeTransformacaoRedeBasicaService.update(onsCapacidadeTransformacaoRedeBasica));
    } else {
      this.subscribeToSaveResponse(this.onsCapacidadeTransformacaoRedeBasicaService.create(onsCapacidadeTransformacaoRedeBasica));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>>): void {
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

  protected updateForm(onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica): void {
    this.onsCapacidadeTransformacaoRedeBasica = onsCapacidadeTransformacaoRedeBasica;
    this.onsCapacidadeTransformacaoRedeBasicaFormService.resetForm(this.editForm, onsCapacidadeTransformacaoRedeBasica);
  }
}
