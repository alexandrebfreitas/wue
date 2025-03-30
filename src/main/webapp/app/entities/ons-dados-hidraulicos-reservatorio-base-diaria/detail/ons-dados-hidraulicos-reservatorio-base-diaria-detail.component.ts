import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsDadosHidraulicosReservatorioBaseDiaria } from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';

@Component({
  selector: 'jhi-ons-dados-hidraulicos-reservatorio-base-diaria-detail',
  templateUrl: './ons-dados-hidraulicos-reservatorio-base-diaria-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent {
  onsDadosHidraulicosReservatorioBaseDiaria = input<IOnsDadosHidraulicosReservatorioBaseDiaria | null>(null);

  previousState(): void {
    window.history.back();
  }
}
