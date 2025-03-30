import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';

@Component({
  selector: 'jhi-ons-dados-valores-previsao-versus-programado-eolicas-e-solares-detail',
  templateUrl: './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent {
  onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = input<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares | null>(null);

  previousState(): void {
    window.history.back();
  }
}
