import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';
import { OnsEarDiarioBaciaService } from '../service/ons-ear-diario-bacia.service';

@Component({
  templateUrl: './ons-ear-diario-bacia-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEarDiarioBaciaDeleteDialogComponent {
  onsEarDiarioBacia?: IOnsEarDiarioBacia;

  protected onsEarDiarioBaciaService = inject(OnsEarDiarioBaciaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEarDiarioBaciaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
