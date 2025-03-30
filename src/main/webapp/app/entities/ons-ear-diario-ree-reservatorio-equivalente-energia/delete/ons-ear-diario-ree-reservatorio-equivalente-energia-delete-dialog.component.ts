import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEarDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ear-diario-ree-reservatorio-equivalente-energia.service';

@Component({
  templateUrl: './ons-ear-diario-ree-reservatorio-equivalente-energia-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEarDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent {
  onsEarDiarioReeReservatorioEquivalenteEnergia?: IOnsEarDiarioReeReservatorioEquivalenteEnergia;

  protected onsEarDiarioReeReservatorioEquivalenteEnergiaService = inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEarDiarioReeReservatorioEquivalenteEnergiaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
