import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';

@Component({
  selector: 'jhi-ons-carga-energia-mensal-detail',
  templateUrl: './ons-carga-energia-mensal-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCargaEnergiaMensalDetailComponent {
  onsCargaEnergiaMensal = input<IOnsCargaEnergiaMensal | null>(null);

  previousState(): void {
    window.history.back();
  }
}
