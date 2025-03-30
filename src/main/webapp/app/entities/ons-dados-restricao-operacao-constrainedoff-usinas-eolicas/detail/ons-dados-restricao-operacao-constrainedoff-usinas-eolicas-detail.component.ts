import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';

@Component({
  selector: 'jhi-ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detail',
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = input<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
