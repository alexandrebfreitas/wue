import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.service';
import {
  OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormGroup,
  OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormService,
} from './ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-form.service';

@Component({
  selector: 'jhi-ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-update',
  templateUrl: './ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoUpdateComponent implements OnInit {
  isSaving = false;
  onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao | null =
    null;

  protected onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService = inject(
    OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService,
  );
  protected onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormService = inject(
    OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormService,
  );
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormGroup =
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormService.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao }) => {
      this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao =
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao;
      if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao) {
        this.updateForm(onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao =
      this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormService.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao(
        this.editForm,
      );
    if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao.id !== null) {
      this.subscribeToSaveResponse(
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService.update(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
        ),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService.create(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
        ),
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao>>,
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
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
  ): void {
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao =
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao;
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoFormService.resetForm(
      this.editForm,
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
    );
  }
}
