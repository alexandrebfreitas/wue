import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.service';

@Component({
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas?: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
