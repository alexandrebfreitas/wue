import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs } from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService } from '../service/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.service';
import {
  OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup,
  OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService,
} from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-form.service';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-update',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent implements OnInit {
  isSaving = false;
  onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs | null =
    null;

  protected onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService,
  );
  protected onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup =
    this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs }) => {
      this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs;
      if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs) {
        this.updateForm(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
      this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(
        this.editForm,
      );
    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.id !== null) {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService.update(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService.create(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>>,
  ): void {
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
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  ): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs;
    this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService.resetForm(
      this.editForm,
      onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
    );
  }
}
