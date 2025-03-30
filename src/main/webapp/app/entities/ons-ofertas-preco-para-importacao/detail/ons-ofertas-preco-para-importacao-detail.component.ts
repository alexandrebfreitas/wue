import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';

@Component({
  selector: 'jhi-ons-ofertas-preco-para-importacao-detail',
  templateUrl: './ons-ofertas-preco-para-importacao-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsOfertasPrecoParaImportacaoDetailComponent {
  onsOfertasPrecoParaImportacao = input<IOnsOfertasPrecoParaImportacao | null>(null);

  previousState(): void {
    window.history.back();
  }
}
