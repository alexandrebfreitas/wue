import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import { OnsEnaDiarioSubsistemaService } from '../service/ons-ena-diario-subsistema.service';

@Component({
  templateUrl: './ons-ena-diario-subsistema-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEnaDiarioSubsistemaDeleteDialogComponent {
  onsEnaDiarioSubsistema?: IOnsEnaDiarioSubsistema;

  protected onsEnaDiarioSubsistemaService = inject(OnsEnaDiarioSubsistemaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEnaDiarioSubsistemaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
