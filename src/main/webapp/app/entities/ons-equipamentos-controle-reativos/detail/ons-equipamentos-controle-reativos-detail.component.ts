import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';

@Component({
  selector: 'jhi-ons-equipamentos-controle-reativos-detail',
  templateUrl: './ons-equipamentos-controle-reativos-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsEquipamentosControleReativosDetailComponent {
  onsEquipamentosControleReativos = input<IOnsEquipamentosControleReativos | null>(null);

  previousState(): void {
    window.history.back();
  }
}
