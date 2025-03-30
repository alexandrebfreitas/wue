import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs } from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-detail',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent {
  onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
    input<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs | null>(null);

  previousState(): void {
    window.history.back();
  }
}
