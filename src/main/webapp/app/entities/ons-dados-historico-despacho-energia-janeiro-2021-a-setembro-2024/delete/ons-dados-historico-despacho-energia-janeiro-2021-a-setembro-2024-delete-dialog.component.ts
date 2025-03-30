import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';

@Component({
  templateUrl: './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent {
  onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024?: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;

  protected onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service = inject(
    OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
