import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';

@Component({
  selector: 'jhi-ons-intercambios-entre-subsistemas-detail',
  templateUrl: './ons-intercambios-entre-subsistemas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsIntercambiosEntreSubsistemasDetailComponent {
  onsIntercambiosEntreSubsistemas = input<IOnsIntercambiosEntreSubsistemas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
