import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';

@Component({
  selector: 'jhi-ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-detail',
  templateUrl: './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent {
  onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
    input<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores | null>(null);

  previousState(): void {
    window.history.back();
  }
}
