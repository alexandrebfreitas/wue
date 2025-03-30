import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';

@Component({
  selector: 'jhi-ons-ear-diario-subsistema-detail',
  templateUrl: './ons-ear-diario-subsistema-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsEarDiarioSubsistemaDetailComponent {
  onsEarDiarioSubsistema = input<IOnsEarDiarioSubsistema | null>(null);

  previousState(): void {
    window.history.back();
  }
}
