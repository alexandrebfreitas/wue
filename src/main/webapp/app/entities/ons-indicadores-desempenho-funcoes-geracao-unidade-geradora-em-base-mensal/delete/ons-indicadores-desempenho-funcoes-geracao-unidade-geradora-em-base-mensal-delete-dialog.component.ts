import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';

@Component({
  templateUrl: './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent {
  onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal?: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal;

  protected onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService = inject(
    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
