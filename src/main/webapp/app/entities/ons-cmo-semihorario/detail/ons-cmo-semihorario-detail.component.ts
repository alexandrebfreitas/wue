import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCmoSemihorario } from '../ons-cmo-semihorario.model';

@Component({
  selector: 'jhi-ons-cmo-semihorario-detail',
  templateUrl: './ons-cmo-semihorario-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCmoSemihorarioDetailComponent {
  onsCmoSemihorario = input<IOnsCmoSemihorario | null>(null);

  previousState(): void {
    window.history.back();
  }
}
