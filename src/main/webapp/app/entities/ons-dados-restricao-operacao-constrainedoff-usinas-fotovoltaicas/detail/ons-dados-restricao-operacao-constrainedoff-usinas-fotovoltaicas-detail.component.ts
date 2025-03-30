import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detail',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = input<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas | null>(
    null,
  );

  previousState(): void {
    window.history.back();
  }
}
