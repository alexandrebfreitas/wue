import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';
import { OnsDadosHidraulicosReservatorioBaseHorariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-horaria.service';

@Component({
  templateUrl: './ons-dados-hidraulicos-reservatorio-base-horaria-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent {
  onsDadosHidraulicosReservatorioBaseHoraria?: IOnsDadosHidraulicosReservatorioBaseHoraria;

  protected onsDadosHidraulicosReservatorioBaseHorariaService = inject(OnsDadosHidraulicosReservatorioBaseHorariaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosHidraulicosReservatorioBaseHorariaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
