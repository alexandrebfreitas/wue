import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';

@Component({
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDeleteDialogComponent {
  onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes?: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes;

  protected onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
