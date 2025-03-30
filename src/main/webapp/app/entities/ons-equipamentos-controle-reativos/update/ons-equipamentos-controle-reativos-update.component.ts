import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';
import { OnsEquipamentosControleReativosService } from '../service/ons-equipamentos-controle-reativos.service';
import {
  OnsEquipamentosControleReativosFormGroup,
  OnsEquipamentosControleReativosFormService,
} from './ons-equipamentos-controle-reativos-form.service';

@Component({
  selector: 'jhi-ons-equipamentos-controle-reativos-update',
  templateUrl: './ons-equipamentos-controle-reativos-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsEquipamentosControleReativosUpdateComponent implements OnInit {
  isSaving = false;
  onsEquipamentosControleReativos: IOnsEquipamentosControleReativos | null = null;

  protected onsEquipamentosControleReativosService = inject(OnsEquipamentosControleReativosService);
  protected onsEquipamentosControleReativosFormService = inject(OnsEquipamentosControleReativosFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsEquipamentosControleReativosFormGroup =
    this.onsEquipamentosControleReativosFormService.createOnsEquipamentosControleReativosFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsEquipamentosControleReativos }) => {
      this.onsEquipamentosControleReativos = onsEquipamentosControleReativos;
      if (onsEquipamentosControleReativos) {
        this.updateForm(onsEquipamentosControleReativos);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsEquipamentosControleReativos = this.onsEquipamentosControleReativosFormService.getOnsEquipamentosControleReativos(
      this.editForm,
    );
    if (onsEquipamentosControleReativos.id !== null) {
      this.subscribeToSaveResponse(this.onsEquipamentosControleReativosService.update(onsEquipamentosControleReativos));
    } else {
      this.subscribeToSaveResponse(this.onsEquipamentosControleReativosService.create(onsEquipamentosControleReativos));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsEquipamentosControleReativos>>): void {
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

  protected updateForm(onsEquipamentosControleReativos: IOnsEquipamentosControleReativos): void {
    this.onsEquipamentosControleReativos = onsEquipamentosControleReativos;
    this.onsEquipamentosControleReativosFormService.resetForm(this.editForm, onsEquipamentosControleReativos);
  }
}
