import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';

@Component({
  selector: 'jhi-ons-importacao-energia-na-modalidade-comercial-bloco-detail',
  templateUrl: './ons-importacao-energia-na-modalidade-comercial-bloco-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent {
  onsImportacaoEnergiaNaModalidadeComercialBloco = input<IOnsImportacaoEnergiaNaModalidadeComercialBloco | null>(null);

  previousState(): void {
    window.history.back();
  }
}
