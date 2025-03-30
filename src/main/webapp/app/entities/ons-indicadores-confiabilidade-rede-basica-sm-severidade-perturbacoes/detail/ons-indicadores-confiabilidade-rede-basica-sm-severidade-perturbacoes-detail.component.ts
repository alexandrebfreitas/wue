import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-detail',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent {
  onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
    input<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes | null>(null);

  previousState(): void {
    window.history.back();
  }
}
