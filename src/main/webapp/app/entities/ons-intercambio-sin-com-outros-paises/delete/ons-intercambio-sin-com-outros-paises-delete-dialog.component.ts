import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';
import { OnsIntercambioSinComOutrosPaisesService } from '../service/ons-intercambio-sin-com-outros-paises.service';

@Component({
  templateUrl: './ons-intercambio-sin-com-outros-paises-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIntercambioSinComOutrosPaisesDeleteDialogComponent {
  onsIntercambioSinComOutrosPaises?: IOnsIntercambioSinComOutrosPaises;

  protected onsIntercambioSinComOutrosPaisesService = inject(OnsIntercambioSinComOutrosPaisesService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIntercambioSinComOutrosPaisesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
