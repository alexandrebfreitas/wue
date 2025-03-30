import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosHidraulicosReservatorioBaseDiaria } from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';
import { OnsDadosHidraulicosReservatorioBaseDiariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-diaria.service';

@Component({
  templateUrl: './ons-dados-hidraulicos-reservatorio-base-diaria-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosHidraulicosReservatorioBaseDiariaDeleteDialogComponent {
  onsDadosHidraulicosReservatorioBaseDiaria?: IOnsDadosHidraulicosReservatorioBaseDiaria;

  protected onsDadosHidraulicosReservatorioBaseDiariaService = inject(OnsDadosHidraulicosReservatorioBaseDiariaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosHidraulicosReservatorioBaseDiariaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
