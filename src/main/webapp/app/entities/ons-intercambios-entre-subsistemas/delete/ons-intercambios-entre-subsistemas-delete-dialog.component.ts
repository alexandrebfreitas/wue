import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';
import { OnsIntercambiosEntreSubsistemasService } from '../service/ons-intercambios-entre-subsistemas.service';

@Component({
  templateUrl: './ons-intercambios-entre-subsistemas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIntercambiosEntreSubsistemasDeleteDialogComponent {
  onsIntercambiosEntreSubsistemas?: IOnsIntercambiosEntreSubsistemas;

  protected onsIntercambiosEntreSubsistemasService = inject(OnsIntercambiosEntreSubsistemasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIntercambiosEntreSubsistemasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
