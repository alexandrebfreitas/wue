import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';

@Component({
  selector: 'jhi-ons-dados-relacionamento-entre-conjuntos-e-usinas-detail',
  templateUrl: './ons-dados-relacionamento-entre-conjuntos-e-usinas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent {
  onsDadosRelacionamentoEntreConjuntosEUsinas = input<IOnsDadosRelacionamentoEntreConjuntosEUsinas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
