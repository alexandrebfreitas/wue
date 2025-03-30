import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';
import { OnsTaxasTeifaETeipService } from '../service/ons-taxas-teifa-e-teip.service';

@Component({
  templateUrl: './ons-taxas-teifa-e-teip-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsTaxasTeifaETeipDeleteDialogComponent {
  onsTaxasTeifaETeip?: IOnsTaxasTeifaETeip;

  protected onsTaxasTeifaETeipService = inject(OnsTaxasTeifaETeipService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsTaxasTeifaETeipService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
