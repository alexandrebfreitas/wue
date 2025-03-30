import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';

@Component({
  selector: 'jhi-ons-ear-diario-ree-reservatorio-equivalente-energia-detail',
  templateUrl: './ons-ear-diario-ree-reservatorio-equivalente-energia-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent {
  onsEarDiarioReeReservatorioEquivalenteEnergia = input<IOnsEarDiarioReeReservatorioEquivalenteEnergia | null>(null);

  previousState(): void {
    window.history.back();
  }
}
