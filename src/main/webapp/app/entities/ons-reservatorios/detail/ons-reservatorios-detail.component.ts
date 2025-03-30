import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsReservatorios } from '../ons-reservatorios.model';

@Component({
  selector: 'jhi-ons-reservatorios-detail',
  templateUrl: './ons-reservatorios-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsReservatoriosDetailComponent {
  onsReservatorios = input<IOnsReservatorios | null>(null);

  previousState(): void {
    window.history.back();
  }
}
