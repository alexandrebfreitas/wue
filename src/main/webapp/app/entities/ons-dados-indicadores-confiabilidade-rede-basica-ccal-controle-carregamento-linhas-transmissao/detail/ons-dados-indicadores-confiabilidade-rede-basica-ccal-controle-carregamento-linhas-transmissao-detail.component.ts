import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.model';

@Component({
  selector: 'jhi-ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-detail',
  templateUrl: './ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoDetailComponent {
  onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao =
    input<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao | null>(null);

  previousState(): void {
    window.history.back();
  }
}
