import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';

@Component({
  selector: 'jhi-ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-detail',
  templateUrl: './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent {
  onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
    input<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual | null>(null);

  previousState(): void {
    window.history.back();
  }
}
