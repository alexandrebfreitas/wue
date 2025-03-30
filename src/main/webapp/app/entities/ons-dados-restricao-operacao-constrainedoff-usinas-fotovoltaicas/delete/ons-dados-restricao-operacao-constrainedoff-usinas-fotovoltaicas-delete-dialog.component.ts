import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';

@Component({
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas?: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
