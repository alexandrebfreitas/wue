import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-detail',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
    input<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
