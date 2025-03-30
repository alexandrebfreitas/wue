import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';
import { OnsDadosDisponibilidadeUsinasService } from '../service/ons-dados-disponibilidade-usinas.service';

@Component({
  templateUrl: './ons-dados-disponibilidade-usinas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosDisponibilidadeUsinasDeleteDialogComponent {
  onsDadosDisponibilidadeUsinas?: IOnsDadosDisponibilidadeUsinas;

  protected onsDadosDisponibilidadeUsinasService = inject(OnsDadosDisponibilidadeUsinasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosDisponibilidadeUsinasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
