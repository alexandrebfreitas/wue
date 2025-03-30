import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';

@Component({
  selector: 'jhi-ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-detail',
  templateUrl: './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent {
  onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
    input<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal | null>(null);

  previousState(): void {
    window.history.back();
  }
}
