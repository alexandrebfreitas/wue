import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';
import { OnsGeracaoTermicaMotivoDespachoService } from '../service/ons-geracao-termica-motivo-despacho.service';

@Component({
  templateUrl: './ons-geracao-termica-motivo-despacho-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent {
  onsGeracaoTermicaMotivoDespacho?: IOnsGeracaoTermicaMotivoDespacho;

  protected onsGeracaoTermicaMotivoDespachoService = inject(OnsGeracaoTermicaMotivoDespachoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsGeracaoTermicaMotivoDespachoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
