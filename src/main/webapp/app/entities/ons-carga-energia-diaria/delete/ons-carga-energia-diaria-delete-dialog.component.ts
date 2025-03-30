import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';
import { OnsCargaEnergiaDiariaService } from '../service/ons-carga-energia-diaria.service';

@Component({
  templateUrl: './ons-carga-energia-diaria-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCargaEnergiaDiariaDeleteDialogComponent {
  onsCargaEnergiaDiaria?: IOnsCargaEnergiaDiaria;

  protected onsCargaEnergiaDiariaService = inject(OnsCargaEnergiaDiariaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCargaEnergiaDiariaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
