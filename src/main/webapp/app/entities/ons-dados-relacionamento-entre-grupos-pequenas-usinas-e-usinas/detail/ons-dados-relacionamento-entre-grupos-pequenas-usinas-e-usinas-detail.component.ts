import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';

@Component({
  selector: 'jhi-ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-detail',
  templateUrl: './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent {
  onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = input<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
