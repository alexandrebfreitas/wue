import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';
import { OnsDadosIntercambioEnergiaModalidadeService } from '../service/ons-dados-intercambio-energia-modalidade.service';

@Component({
  templateUrl: './ons-dados-intercambio-energia-modalidade-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent {
  onsDadosIntercambioEnergiaModalidade?: IOnsDadosIntercambioEnergiaModalidade;

  protected onsDadosIntercambioEnergiaModalidadeService = inject(OnsDadosIntercambioEnergiaModalidadeService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosIntercambioEnergiaModalidadeService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
