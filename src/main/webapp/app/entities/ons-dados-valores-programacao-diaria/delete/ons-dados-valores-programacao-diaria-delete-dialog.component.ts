import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import { OnsDadosValoresProgramacaoDiariaService } from '../service/ons-dados-valores-programacao-diaria.service';

@Component({
  templateUrl: './ons-dados-valores-programacao-diaria-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosValoresProgramacaoDiariaDeleteDialogComponent {
  onsDadosValoresProgramacaoDiaria?: IOnsDadosValoresProgramacaoDiaria;

  protected onsDadosValoresProgramacaoDiariaService = inject(OnsDadosValoresProgramacaoDiariaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosValoresProgramacaoDiariaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
