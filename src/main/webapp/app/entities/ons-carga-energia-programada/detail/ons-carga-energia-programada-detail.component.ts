import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';

@Component({
  selector: 'jhi-ons-carga-energia-programada-detail',
  templateUrl: './ons-carga-energia-programada-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCargaEnergiaProgramadaDetailComponent {
  onsCargaEnergiaProgramada = input<IOnsCargaEnergiaProgramada | null>(null);

  previousState(): void {
    window.history.back();
  }
}
