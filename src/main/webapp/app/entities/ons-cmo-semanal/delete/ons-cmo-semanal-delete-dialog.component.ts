import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';
import { OnsCmoSemanalService } from '../service/ons-cmo-semanal.service';

@Component({
  templateUrl: './ons-cmo-semanal-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCmoSemanalDeleteDialogComponent {
  onsCmoSemanal?: IOnsCmoSemanal;

  protected onsCmoSemanalService = inject(OnsCmoSemanalService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCmoSemanalService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
