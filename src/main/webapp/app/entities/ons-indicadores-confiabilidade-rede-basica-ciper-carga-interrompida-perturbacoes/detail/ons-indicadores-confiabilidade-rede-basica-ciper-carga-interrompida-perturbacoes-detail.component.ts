import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-detail',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent {
  onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
    input<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes | null>(null);

  previousState(): void {
    window.history.back();
  }
}
