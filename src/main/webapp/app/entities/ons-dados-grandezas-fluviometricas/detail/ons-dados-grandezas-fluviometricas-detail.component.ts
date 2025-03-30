import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';

@Component({
  selector: 'jhi-ons-dados-grandezas-fluviometricas-detail',
  templateUrl: './ons-dados-grandezas-fluviometricas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosGrandezasFluviometricasDetailComponent {
  onsDadosGrandezasFluviometricas = input<IOnsDadosGrandezasFluviometricas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
