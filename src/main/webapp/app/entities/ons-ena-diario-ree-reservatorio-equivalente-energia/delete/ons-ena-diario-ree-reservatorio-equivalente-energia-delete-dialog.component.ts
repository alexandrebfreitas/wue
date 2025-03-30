import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ena-diario-ree-reservatorio-equivalente-energia.service';

@Component({
  templateUrl: './ons-ena-diario-ree-reservatorio-equivalente-energia-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent {
  onsEnaDiarioReeReservatorioEquivalenteEnergia?: IOnsEnaDiarioReeReservatorioEquivalenteEnergia;

  protected onsEnaDiarioReeReservatorioEquivalenteEnergiaService = inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsEnaDiarioReeReservatorioEquivalenteEnergiaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
