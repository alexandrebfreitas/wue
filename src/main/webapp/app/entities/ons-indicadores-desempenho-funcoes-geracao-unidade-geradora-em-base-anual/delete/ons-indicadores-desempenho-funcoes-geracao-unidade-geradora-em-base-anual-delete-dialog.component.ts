import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';

@Component({
  templateUrl: './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent {
  onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual?: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual;

  protected onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService = inject(
    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
