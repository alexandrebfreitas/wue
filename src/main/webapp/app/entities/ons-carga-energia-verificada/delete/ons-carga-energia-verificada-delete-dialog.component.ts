import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';
import { OnsCargaEnergiaVerificadaService } from '../service/ons-carga-energia-verificada.service';

@Component({
  templateUrl: './ons-carga-energia-verificada-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCargaEnergiaVerificadaDeleteDialogComponent {
  onsCargaEnergiaVerificada?: IOnsCargaEnergiaVerificada;

  protected onsCargaEnergiaVerificadaService = inject(OnsCargaEnergiaVerificadaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCargaEnergiaVerificadaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
