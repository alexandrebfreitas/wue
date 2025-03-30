import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';

@Component({
  selector: 'jhi-ons-geracao-comercial-para-exportacao-internacional-detail',
  templateUrl: './ons-geracao-comercial-para-exportacao-internacional-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsGeracaoComercialParaExportacaoInternacionalDetailComponent {
  onsGeracaoComercialParaExportacaoInternacional = input<IOnsGeracaoComercialParaExportacaoInternacional | null>(null);

  previousState(): void {
    window.history.back();
  }
}
