import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';
import { OnsDadosRelacionamentoEntreConjuntosEUsinasService } from '../service/ons-dados-relacionamento-entre-conjuntos-e-usinas.service';

@Component({
  templateUrl: './ons-dados-relacionamento-entre-conjuntos-e-usinas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent {
  onsDadosRelacionamentoEntreConjuntosEUsinas?: IOnsDadosRelacionamentoEntreConjuntosEUsinas;

  protected onsDadosRelacionamentoEntreConjuntosEUsinasService = inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosRelacionamentoEntreConjuntosEUsinasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
