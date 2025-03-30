import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';

@Component({
  selector: 'jhi-ons-ena-diario-reservatorio-detail',
  templateUrl: './ons-ena-diario-reservatorio-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsEnaDiarioReservatorioDetailComponent {
  onsEnaDiarioReservatorio = input<IOnsEnaDiarioReservatorio | null>(null);

  previousState(): void {
    window.history.back();
  }
}
