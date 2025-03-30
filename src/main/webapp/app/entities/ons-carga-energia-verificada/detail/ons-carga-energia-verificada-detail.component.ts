import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';

@Component({
  selector: 'jhi-ons-carga-energia-verificada-detail',
  templateUrl: './ons-carga-energia-verificada-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCargaEnergiaVerificadaDetailComponent {
  onsCargaEnergiaVerificada = input<IOnsCargaEnergiaVerificada | null>(null);

  previousState(): void {
    window.history.back();
  }
}
