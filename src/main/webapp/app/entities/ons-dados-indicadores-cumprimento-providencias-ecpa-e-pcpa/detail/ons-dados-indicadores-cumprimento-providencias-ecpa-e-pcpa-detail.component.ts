import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';

@Component({
  selector: 'jhi-ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-detail',
  templateUrl: './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent {
  onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = input<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa | null>(null);

  previousState(): void {
    window.history.back();
  }
}
