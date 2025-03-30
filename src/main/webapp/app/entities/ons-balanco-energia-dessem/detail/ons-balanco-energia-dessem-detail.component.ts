import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';

@Component({
  selector: 'jhi-ons-balanco-energia-dessem-detail',
  templateUrl: './ons-balanco-energia-dessem-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsBalancoEnergiaDessemDetailComponent {
  onsBalancoEnergiaDessem = input<IOnsBalancoEnergiaDessem | null>(null);

  previousState(): void {
    window.history.back();
  }
}
