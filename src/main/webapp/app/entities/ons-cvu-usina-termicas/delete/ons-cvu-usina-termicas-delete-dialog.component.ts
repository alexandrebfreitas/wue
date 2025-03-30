import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';
import { OnsCvuUsinaTermicasService } from '../service/ons-cvu-usina-termicas.service';

@Component({
  templateUrl: './ons-cvu-usina-termicas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCvuUsinaTermicasDeleteDialogComponent {
  onsCvuUsinaTermicas?: IOnsCvuUsinaTermicas;

  protected onsCvuUsinaTermicasService = inject(OnsCvuUsinaTermicasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCvuUsinaTermicasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
