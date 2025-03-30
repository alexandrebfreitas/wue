import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos } from '../ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.model';

@Component({
  selector: 'jhi-ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-detail',
  templateUrl: './ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosDetailComponent {
  onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos =
    input<IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos | null>(null);

  previousState(): void {
    window.history.back();
  }
}
