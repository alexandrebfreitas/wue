import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';
import { OnsCargaEnergiaProgramadaService } from '../service/ons-carga-energia-programada.service';

@Component({
  templateUrl: './ons-carga-energia-programada-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCargaEnergiaProgramadaDeleteDialogComponent {
  onsCargaEnergiaProgramada?: IOnsCargaEnergiaProgramada;

  protected onsCargaEnergiaProgramadaService = inject(OnsCargaEnergiaProgramadaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCargaEnergiaProgramadaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
