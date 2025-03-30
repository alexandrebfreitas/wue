import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs } from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService } from '../service/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.service';

@Component({
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDeleteDialogComponent {
  onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs?: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs;

  protected onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
