import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';

@Component({
  selector: 'jhi-ons-ena-diario-subsistema-detail',
  templateUrl: './ons-ena-diario-subsistema-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsEnaDiarioSubsistemaDetailComponent {
  onsEnaDiarioSubsistema = input<IOnsEnaDiarioSubsistema | null>(null);

  previousState(): void {
    window.history.back();
  }
}
