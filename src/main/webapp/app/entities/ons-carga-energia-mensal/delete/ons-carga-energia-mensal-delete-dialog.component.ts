import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';
import { OnsCargaEnergiaMensalService } from '../service/ons-carga-energia-mensal.service';

@Component({
  templateUrl: './ons-carga-energia-mensal-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCargaEnergiaMensalDeleteDialogComponent {
  onsCargaEnergiaMensal?: IOnsCargaEnergiaMensal;

  protected onsCargaEnergiaMensalService = inject(OnsCargaEnergiaMensalService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCargaEnergiaMensalService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
