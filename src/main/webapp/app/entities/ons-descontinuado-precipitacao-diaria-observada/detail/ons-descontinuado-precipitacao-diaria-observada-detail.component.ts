import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDescontinuadoPrecipitacaoDiariaObservada } from '../ons-descontinuado-precipitacao-diaria-observada.model';

@Component({
  selector: 'jhi-ons-descontinuado-precipitacao-diaria-observada-detail',
  templateUrl: './ons-descontinuado-precipitacao-diaria-observada-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent {
  onsDescontinuadoPrecipitacaoDiariaObservada = input<IOnsDescontinuadoPrecipitacaoDiariaObservada | null>(null);

  previousState(): void {
    window.history.back();
  }
}
