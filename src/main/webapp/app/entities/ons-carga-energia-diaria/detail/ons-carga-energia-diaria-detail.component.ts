import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';

@Component({
  selector: 'jhi-ons-carga-energia-diaria-detail',
  templateUrl: './ons-carga-energia-diaria-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCargaEnergiaDiariaDetailComponent {
  onsCargaEnergiaDiaria = input<IOnsCargaEnergiaDiaria | null>(null);

  previousState(): void {
    window.history.back();
  }
}
