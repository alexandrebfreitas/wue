import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import { OnsEnaDiarioReservatorioService } from '../service/ons-ena-diario-reservatorio.service';

@Component({
  templateUrl: './ons-ena-diario-reservatorio-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEnaDiarioReservatorioDeleteDialogComponent {
  onsEnaDiarioReservatorio?: IOnsEnaDiarioReservatorio;

  protected onsEnaDiarioReservatorioService = inject(OnsEnaDiarioReservatorioService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEnaDiarioReservatorioService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
