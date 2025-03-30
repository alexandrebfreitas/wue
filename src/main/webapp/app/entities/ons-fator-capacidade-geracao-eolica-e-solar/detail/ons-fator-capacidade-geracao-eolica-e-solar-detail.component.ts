import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';

@Component({
  selector: 'jhi-ons-fator-capacidade-geracao-eolica-e-solar-detail',
  templateUrl: './ons-fator-capacidade-geracao-eolica-e-solar-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent {
  onsFatorCapacidadeGeracaoEolicaESolar = input<IOnsFatorCapacidadeGeracaoEolicaESolar | null>(null);

  previousState(): void {
    window.history.back();
  }
}
