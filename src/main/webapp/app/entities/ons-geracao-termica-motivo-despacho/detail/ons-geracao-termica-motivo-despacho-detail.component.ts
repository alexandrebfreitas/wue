import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';

@Component({
  selector: 'jhi-ons-geracao-termica-motivo-despacho-detail',
  templateUrl: './ons-geracao-termica-motivo-despacho-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsGeracaoTermicaMotivoDespachoDetailComponent {
  onsGeracaoTermicaMotivoDespacho = input<IOnsGeracaoTermicaMotivoDespacho | null>(null);

  previousState(): void {
    window.history.back();
  }
}
