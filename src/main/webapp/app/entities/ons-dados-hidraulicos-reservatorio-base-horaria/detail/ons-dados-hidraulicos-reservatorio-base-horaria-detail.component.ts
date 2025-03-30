import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';

@Component({
  selector: 'jhi-ons-dados-hidraulicos-reservatorio-base-horaria-detail',
  templateUrl: './ons-dados-hidraulicos-reservatorio-base-horaria-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent {
  onsDadosHidraulicosReservatorioBaseHoraria = input<IOnsDadosHidraulicosReservatorioBaseHoraria | null>(null);

  previousState(): void {
    window.history.back();
  }
}
