import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.model';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-detail',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDetailComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas =
    input<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
