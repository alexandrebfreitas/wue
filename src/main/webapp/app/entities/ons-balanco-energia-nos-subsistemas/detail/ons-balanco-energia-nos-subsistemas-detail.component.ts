import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';

@Component({
  selector: 'jhi-ons-balanco-energia-nos-subsistemas-detail',
  templateUrl: './ons-balanco-energia-nos-subsistemas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsBalancoEnergiaNosSubsistemasDetailComponent {
  onsBalancoEnergiaNosSubsistemas = input<IOnsBalancoEnergiaNosSubsistemas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
