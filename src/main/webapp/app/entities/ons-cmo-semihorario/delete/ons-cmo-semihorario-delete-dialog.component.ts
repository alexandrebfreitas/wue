import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCmoSemihorario } from '../ons-cmo-semihorario.model';
import { OnsCmoSemihorarioService } from '../service/ons-cmo-semihorario.service';

@Component({
  templateUrl: './ons-cmo-semihorario-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCmoSemihorarioDeleteDialogComponent {
  onsCmoSemihorario?: IOnsCmoSemihorario;

  protected onsCmoSemihorarioService = inject(OnsCmoSemihorarioService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCmoSemihorarioService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
