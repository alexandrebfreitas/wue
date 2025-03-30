import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosProgramadosElementosFluxoControlado } from '../ons-dados-programados-elementos-fluxo-controlado.model';

@Component({
  selector: 'jhi-ons-dados-programados-elementos-fluxo-controlado-detail',
  templateUrl: './ons-dados-programados-elementos-fluxo-controlado-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosProgramadosElementosFluxoControladoDetailComponent {
  onsDadosProgramadosElementosFluxoControlado = input<IOnsDadosProgramadosElementosFluxoControlado | null>(null);

  previousState(): void {
    window.history.back();
  }
}
