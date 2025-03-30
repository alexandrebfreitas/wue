import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';
import { OnsEquipamentosControleReativosService } from '../service/ons-equipamentos-controle-reativos.service';

@Component({
  templateUrl: './ons-equipamentos-controle-reativos-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEquipamentosControleReativosDeleteDialogComponent {
  onsEquipamentosControleReativos?: IOnsEquipamentosControleReativos;

  protected onsEquipamentosControleReativosService = inject(OnsEquipamentosControleReativosService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEquipamentosControleReativosService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
