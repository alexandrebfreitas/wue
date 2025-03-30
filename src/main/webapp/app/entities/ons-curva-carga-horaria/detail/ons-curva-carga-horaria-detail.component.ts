import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';

@Component({
  selector: 'jhi-ons-curva-carga-horaria-detail',
  templateUrl: './ons-curva-carga-horaria-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCurvaCargaHorariaDetailComponent {
  onsCurvaCargaHoraria = input<IOnsCurvaCargaHoraria | null>(null);

  previousState(): void {
    window.history.back();
  }
}
