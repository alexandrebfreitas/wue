import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.service';

@Component({
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDeleteDialogComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas?: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
