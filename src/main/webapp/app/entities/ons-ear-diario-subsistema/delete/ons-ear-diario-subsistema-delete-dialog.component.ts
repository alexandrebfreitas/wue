import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';
import { OnsEarDiarioSubsistemaService } from '../service/ons-ear-diario-subsistema.service';

@Component({
  templateUrl: './ons-ear-diario-subsistema-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEarDiarioSubsistemaDeleteDialogComponent {
  onsEarDiarioSubsistema?: IOnsEarDiarioSubsistema;

  protected onsEarDiarioSubsistemaService = inject(OnsEarDiarioSubsistemaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEarDiarioSubsistemaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
