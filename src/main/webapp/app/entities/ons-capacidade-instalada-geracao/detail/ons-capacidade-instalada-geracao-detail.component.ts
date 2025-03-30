import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';

@Component({
  selector: 'jhi-ons-capacidade-instalada-geracao-detail',
  templateUrl: './ons-capacidade-instalada-geracao-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCapacidadeInstaladaGeracaoDetailComponent {
  onsCapacidadeInstaladaGeracao = input<IOnsCapacidadeInstaladaGeracao | null>(null);

  previousState(): void {
    window.history.back();
  }
}
