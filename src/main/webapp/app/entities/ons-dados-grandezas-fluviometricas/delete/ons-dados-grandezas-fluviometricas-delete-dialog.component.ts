import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';
import { OnsDadosGrandezasFluviometricasService } from '../service/ons-dados-grandezas-fluviometricas.service';

@Component({
  templateUrl: './ons-dados-grandezas-fluviometricas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosGrandezasFluviometricasDeleteDialogComponent {
  onsDadosGrandezasFluviometricas?: IOnsDadosGrandezasFluviometricas;

  protected onsDadosGrandezasFluviometricasService = inject(OnsDadosGrandezasFluviometricasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosGrandezasFluviometricasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
