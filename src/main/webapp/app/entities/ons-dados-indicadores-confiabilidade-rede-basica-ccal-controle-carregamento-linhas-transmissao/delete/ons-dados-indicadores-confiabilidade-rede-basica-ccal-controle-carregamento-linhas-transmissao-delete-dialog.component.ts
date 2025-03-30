import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.service';

@Component({
  templateUrl:
    './ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoDeleteDialogComponent {
  onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao?: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao;

  protected onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService = inject(
    OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
