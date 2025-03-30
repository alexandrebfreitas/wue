import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';
import { OnsCurvaCargaHorariaService } from '../service/ons-curva-carga-horaria.service';

@Component({
  templateUrl: './ons-curva-carga-horaria-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsCurvaCargaHorariaDeleteDialogComponent {
  onsCurvaCargaHoraria?: IOnsCurvaCargaHoraria;

  protected onsCurvaCargaHorariaService = inject(OnsCurvaCargaHorariaService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsCurvaCargaHorariaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
