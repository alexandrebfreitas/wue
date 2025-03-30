import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';

@Component({
  selector: 'jhi-ons-dados-valores-programacao-diaria-detail',
  templateUrl: './ons-dados-valores-programacao-diaria-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosValoresProgramacaoDiariaDetailComponent {
  onsDadosValoresProgramacaoDiaria = input<IOnsDadosValoresProgramacaoDiaria | null>(null);

  previousState(): void {
    window.history.back();
  }
}
