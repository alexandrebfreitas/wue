import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';
import { OnsBalancoEnergiaNosSubsistemasService } from '../service/ons-balanco-energia-nos-subsistemas.service';

@Component({
  templateUrl: './ons-balanco-energia-nos-subsistemas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent {
  onsBalancoEnergiaNosSubsistemas?: IOnsBalancoEnergiaNosSubsistemas;

  protected onsBalancoEnergiaNosSubsistemasService = inject(OnsBalancoEnergiaNosSubsistemasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsBalancoEnergiaNosSubsistemasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
