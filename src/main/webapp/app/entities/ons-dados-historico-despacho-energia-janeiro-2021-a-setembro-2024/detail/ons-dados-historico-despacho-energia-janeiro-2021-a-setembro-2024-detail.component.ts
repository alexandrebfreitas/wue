import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';

@Component({
  selector: 'jhi-ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-detail',
  templateUrl: './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent {
  onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = input<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 | null>(null);

  previousState(): void {
    window.history.back();
  }
}
