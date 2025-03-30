import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';

@Component({
  selector: 'jhi-ons-capacidade-transformacao-rede-basica-detail',
  templateUrl: './ons-capacidade-transformacao-rede-basica-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCapacidadeTransformacaoRedeBasicaDetailComponent {
  onsCapacidadeTransformacaoRedeBasica = input<IOnsCapacidadeTransformacaoRedeBasica | null>(null);

  previousState(): void {
    window.history.back();
  }
}
