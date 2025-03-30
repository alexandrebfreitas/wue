import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-dreq-freq-detail',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-dreq-freq-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent {
  onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = input<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq | null>(null);

  previousState(): void {
    window.history.back();
  }
}
