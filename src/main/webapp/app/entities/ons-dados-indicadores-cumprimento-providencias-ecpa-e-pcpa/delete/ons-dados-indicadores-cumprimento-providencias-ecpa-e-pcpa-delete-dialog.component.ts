import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';
import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService } from '../service/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';

@Component({
  templateUrl: './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent {
  onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa?: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa;

  protected onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService = inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
