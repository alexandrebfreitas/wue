import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';
import { OnsDadosHidrologicosVolumeEsperaRecomendadoService } from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';

@Component({
  templateUrl: './ons-dados-hidrologicos-volume-espera-recomendado-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent {
  onsDadosHidrologicosVolumeEsperaRecomendado?: IOnsDadosHidrologicosVolumeEsperaRecomendado;

  protected onsDadosHidrologicosVolumeEsperaRecomendadoService = inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosHidrologicosVolumeEsperaRecomendadoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
