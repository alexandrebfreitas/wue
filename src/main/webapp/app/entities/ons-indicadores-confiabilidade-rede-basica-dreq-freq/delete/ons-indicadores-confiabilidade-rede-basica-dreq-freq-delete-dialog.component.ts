import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';

@Component({
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-dreq-freq-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent {
  onsIndicadoresConfiabilidadeRedeBasicaDreqFreq?: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq;

  protected onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService = inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
