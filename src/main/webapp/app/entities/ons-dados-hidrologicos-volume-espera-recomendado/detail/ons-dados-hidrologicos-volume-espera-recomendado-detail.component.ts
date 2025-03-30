import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';

@Component({
  selector: 'jhi-ons-dados-hidrologicos-volume-espera-recomendado-detail',
  templateUrl: './ons-dados-hidrologicos-volume-espera-recomendado-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent {
  onsDadosHidrologicosVolumeEsperaRecomendado = input<IOnsDadosHidrologicosVolumeEsperaRecomendado | null>(null);

  previousState(): void {
    window.history.back();
  }
}
