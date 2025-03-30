import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';
import { OnsOfertasPrecoParaImportacaoService } from '../service/ons-ofertas-preco-para-importacao.service';

@Component({
  templateUrl: './ons-ofertas-preco-para-importacao-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsOfertasPrecoParaImportacaoDeleteDialogComponent {
  onsOfertasPrecoParaImportacao?: IOnsOfertasPrecoParaImportacao;

  protected onsOfertasPrecoParaImportacaoService = inject(OnsOfertasPrecoParaImportacaoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsOfertasPrecoParaImportacaoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
