import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';

@Component({
  selector: 'jhi-ons-intercambio-sin-com-outros-paises-detail',
  templateUrl: './ons-intercambio-sin-com-outros-paises-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsIntercambioSinComOutrosPaisesDetailComponent {
  onsIntercambioSinComOutrosPaises = input<IOnsIntercambioSinComOutrosPaises | null>(null);

  previousState(): void {
    window.history.back();
  }
}
