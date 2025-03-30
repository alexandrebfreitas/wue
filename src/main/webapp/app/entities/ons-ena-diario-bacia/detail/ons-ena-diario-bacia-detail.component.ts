import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';

@Component({
  selector: 'jhi-ons-ena-diario-bacia-detail',
  templateUrl: './ons-ena-diario-bacia-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsEnaDiarioBaciaDetailComponent {
  onsEnaDiarioBacia = input<IOnsEnaDiarioBacia | null>(null);

  previousState(): void {
    window.history.back();
  }
}
