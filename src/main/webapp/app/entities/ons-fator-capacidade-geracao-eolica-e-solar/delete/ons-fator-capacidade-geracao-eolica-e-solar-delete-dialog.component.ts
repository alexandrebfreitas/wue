import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';
import { OnsFatorCapacidadeGeracaoEolicaESolarService } from '../service/ons-fator-capacidade-geracao-eolica-e-solar.service';

@Component({
  templateUrl: './ons-fator-capacidade-geracao-eolica-e-solar-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent {
  onsFatorCapacidadeGeracaoEolicaESolar?: IOnsFatorCapacidadeGeracaoEolicaESolar;

  protected onsFatorCapacidadeGeracaoEolicaESolarService = inject(OnsFatorCapacidadeGeracaoEolicaESolarService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsFatorCapacidadeGeracaoEolicaESolarService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
