import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.service';

@Component({
  templateUrl: './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent {
  onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas?: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas;

  protected onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService = inject(
    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
