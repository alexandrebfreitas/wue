import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';
import { OnsModalidadeOperacaoUsinasService } from '../service/ons-modalidade-operacao-usinas.service';

@Component({
  templateUrl: './ons-modalidade-operacao-usinas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsModalidadeOperacaoUsinasDeleteDialogComponent {
  onsModalidadeOperacaoUsinas?: IOnsModalidadeOperacaoUsinas;

  protected onsModalidadeOperacaoUsinasService = inject(OnsModalidadeOperacaoUsinasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsModalidadeOperacaoUsinasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
