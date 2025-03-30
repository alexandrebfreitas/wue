import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';

@Component({
  selector: 'jhi-ons-geracao-usina-em-base-horaria-detail',
  templateUrl: './ons-geracao-usina-em-base-horaria-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsGeracaoUsinaEmBaseHorariaDetailComponent {
  onsGeracaoUsinaEmBaseHoraria = input<IOnsGeracaoUsinaEmBaseHoraria | null>(null);

  previousState(): void {
    window.history.back();
  }
}
