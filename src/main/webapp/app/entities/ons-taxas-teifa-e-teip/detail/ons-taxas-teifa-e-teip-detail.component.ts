import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';

@Component({
  selector: 'jhi-ons-taxas-teifa-e-teip-detail',
  templateUrl: './ons-taxas-teifa-e-teip-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsTaxasTeifaETeipDetailComponent {
  onsTaxasTeifaETeip = input<IOnsTaxasTeifaETeip | null>(null);

  previousState(): void {
    window.history.back();
  }
}
