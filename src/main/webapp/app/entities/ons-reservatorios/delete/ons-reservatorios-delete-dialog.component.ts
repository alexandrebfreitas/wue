import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsReservatorios } from '../ons-reservatorios.model';
import { OnsReservatoriosService } from '../service/ons-reservatorios.service';

@Component({
  templateUrl: './ons-reservatorios-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsReservatoriosDeleteDialogComponent {
  onsReservatorios?: IOnsReservatorios;

  protected onsReservatoriosService = inject(OnsReservatoriosService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsReservatoriosService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
