import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';
import { OnsCapacidadeInstaladaGeracaoService } from '../service/ons-capacidade-instalada-geracao.service';

@Component({
  templateUrl: './ons-capacidade-instalada-geracao-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCapacidadeInstaladaGeracaoDeleteDialogComponent {
  onsCapacidadeInstaladaGeracao?: IOnsCapacidadeInstaladaGeracao;

  protected onsCapacidadeInstaladaGeracaoService = inject(OnsCapacidadeInstaladaGeracaoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCapacidadeInstaladaGeracaoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
