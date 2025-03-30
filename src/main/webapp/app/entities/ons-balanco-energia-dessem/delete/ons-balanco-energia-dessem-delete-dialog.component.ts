import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';
import { OnsBalancoEnergiaDessemService } from '../service/ons-balanco-energia-dessem.service';

@Component({
  templateUrl: './ons-balanco-energia-dessem-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsBalancoEnergiaDessemDeleteDialogComponent {
  onsBalancoEnergiaDessem?: IOnsBalancoEnergiaDessem;

  protected onsBalancoEnergiaDessemService = inject(OnsBalancoEnergiaDessemService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsBalancoEnergiaDessemService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
