import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';
import { OnsGeracaoUsinaEmBaseHorariaService } from '../service/ons-geracao-usina-em-base-horaria.service';

@Component({
  templateUrl: './ons-geracao-usina-em-base-horaria-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent {
  onsGeracaoUsinaEmBaseHoraria?: IOnsGeracaoUsinaEmBaseHoraria;

  protected onsGeracaoUsinaEmBaseHorariaService = inject(OnsGeracaoUsinaEmBaseHorariaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsGeracaoUsinaEmBaseHorariaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
