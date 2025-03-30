import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';
import { OnsImportacaoEnergiaNaModalidadeComercialBlocoService } from '../service/ons-importacao-energia-na-modalidade-comercial-bloco.service';

@Component({
  templateUrl: './ons-importacao-energia-na-modalidade-comercial-bloco-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsImportacaoEnergiaNaModalidadeComercialBlocoDeleteDialogComponent {
  onsImportacaoEnergiaNaModalidadeComercialBloco?: IOnsImportacaoEnergiaNaModalidadeComercialBloco;

  protected onsImportacaoEnergiaNaModalidadeComercialBlocoService = inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsImportacaoEnergiaNaModalidadeComercialBlocoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
