import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';

@Component({
  selector: 'jhi-ons-modalidade-operacao-usinas-detail',
  templateUrl: './ons-modalidade-operacao-usinas-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class OnsModalidadeOperacaoUsinasDetailComponent {
  onsModalidadeOperacaoUsinas = input<IOnsModalidadeOperacaoUsinas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
