import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';

@Component({
  selector: 'jhi-ons-dados-intercambio-energia-modalidade-detail',
  templateUrl: './ons-dados-intercambio-energia-modalidade-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosIntercambioEnergiaModalidadeDetailComponent {
  onsDadosIntercambioEnergiaModalidade = input<IOnsDadosIntercambioEnergiaModalidade | null>(null);

  previousState(): void {
    window.history.back();
  }
}
