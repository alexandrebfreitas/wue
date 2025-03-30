import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';

@Component({
  selector: 'jhi-ons-ear-diario-bacia-detail',
  templateUrl: './ons-ear-diario-bacia-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsEarDiarioBaciaDetailComponent {
  onsEarDiarioBacia = input<IOnsEarDiarioBacia | null>(null);

  previousState(): void {
    window.history.back();
  }
}
