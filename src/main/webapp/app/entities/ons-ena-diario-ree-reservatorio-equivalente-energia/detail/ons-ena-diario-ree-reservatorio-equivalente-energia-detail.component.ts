import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';

@Component({
  selector: 'jhi-ons-ena-diario-ree-reservatorio-equivalente-energia-detail',
  templateUrl: './ons-ena-diario-ree-reservatorio-equivalente-energia-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsEnaDiarioReeReservatorioEquivalenteEnergiaDetailComponent {
  onsEnaDiarioReeReservatorioEquivalenteEnergia = input<IOnsEnaDiarioReeReservatorioEquivalenteEnergia | null>(null);

  previousState(): void {
    window.history.back();
  }
}
