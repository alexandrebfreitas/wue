import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';
import { OnsEnaDiarioBaciaService } from '../service/ons-ena-diario-bacia.service';

@Component({
  templateUrl: './ons-ena-diario-bacia-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEnaDiarioBaciaDeleteDialogComponent {
  onsEnaDiarioBacia?: IOnsEnaDiarioBacia;

  protected onsEnaDiarioBaciaService = inject(OnsEnaDiarioBaciaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEnaDiarioBaciaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
