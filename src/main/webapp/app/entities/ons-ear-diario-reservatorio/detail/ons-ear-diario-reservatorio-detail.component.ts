import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';

@Component({
  selector: 'jhi-ons-ear-diario-reservatorio-detail',
  templateUrl: './ons-ear-diario-reservatorio-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsEarDiarioReservatorioDetailComponent {
  onsEarDiarioReservatorio = input<IOnsEarDiarioReservatorio | null>(null);

  previousState(): void {
    window.history.back();
  }
}
