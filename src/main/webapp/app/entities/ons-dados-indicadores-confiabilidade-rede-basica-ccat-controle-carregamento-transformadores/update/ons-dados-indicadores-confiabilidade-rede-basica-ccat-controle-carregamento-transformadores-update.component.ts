import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.service';
import {
  OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup,
  OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService,
} from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-form.service';

@Component({
  selector: 'jhi-ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-update',
  templateUrl: './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores | null =
    null;

  protected onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService = inject(
    OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService,
  );
  protected onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService = inject(
    OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup =
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores }) => {
      this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores;
      if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores) {
        this.updateForm(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
      this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
        this.editForm,
      );
    if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService.update(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService.create(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>>,
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
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  ): void {
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores;
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService.resetForm(
      this.editForm,
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
    );
  }
}
