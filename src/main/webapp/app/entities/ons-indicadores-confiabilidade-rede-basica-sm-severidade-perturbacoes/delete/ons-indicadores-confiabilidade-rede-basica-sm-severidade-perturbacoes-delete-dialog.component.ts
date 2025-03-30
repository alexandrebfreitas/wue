import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';

@Component({
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent {
  onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes?: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;

  protected onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
