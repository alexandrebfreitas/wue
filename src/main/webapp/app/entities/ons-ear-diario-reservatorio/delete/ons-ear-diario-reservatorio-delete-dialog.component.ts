import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';
import { OnsEarDiarioReservatorioService } from '../service/ons-ear-diario-reservatorio.service';

@Component({
  templateUrl: './ons-ear-diario-reservatorio-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEarDiarioReservatorioDeleteDialogComponent {
  onsEarDiarioReservatorio?: IOnsEarDiarioReservatorio;

  protected onsEarDiarioReservatorioService = inject(OnsEarDiarioReservatorioService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEarDiarioReservatorioService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
