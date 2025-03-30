import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';

@Component({
  selector: 'jhi-ons-cmo-semanal-detail',
  templateUrl: './ons-cmo-semanal-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCmoSemanalDetailComponent {
  onsCmoSemanal = input<IOnsCmoSemanal | null>(null);

  previousState(): void {
    window.history.back();
  }
}
