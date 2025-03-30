import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';

@Component({
  selector: 'jhi-ons-linhas-transmissao-rede-operacao-detail',
  templateUrl: './ons-linhas-transmissao-rede-operacao-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsLinhasTransmissaoRedeOperacaoDetailComponent {
  onsLinhasTransmissaoRedeOperacao = input<IOnsLinhasTransmissaoRedeOperacao | null>(null);

  previousState(): void {
    window.history.back();
  }
}
