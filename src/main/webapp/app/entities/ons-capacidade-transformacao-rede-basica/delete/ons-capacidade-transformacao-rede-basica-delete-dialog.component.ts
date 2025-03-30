import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';
import { OnsCapacidadeTransformacaoRedeBasicaService } from '../service/ons-capacidade-transformacao-rede-basica.service';

@Component({
  templateUrl: './ons-capacidade-transformacao-rede-basica-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent {
  onsCapacidadeTransformacaoRedeBasica?: IOnsCapacidadeTransformacaoRedeBasica;

  protected onsCapacidadeTransformacaoRedeBasicaService = inject(OnsCapacidadeTransformacaoRedeBasicaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCapacidadeTransformacaoRedeBasicaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
