import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';
import { OnsGeracaoComercialParaExportacaoInternacionalService } from '../service/ons-geracao-comercial-para-exportacao-internacional.service';

@Component({
  templateUrl: './ons-geracao-comercial-para-exportacao-internacional-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent {
  onsGeracaoComercialParaExportacaoInternacional?: IOnsGeracaoComercialParaExportacaoInternacional;

  protected onsGeracaoComercialParaExportacaoInternacionalService = inject(OnsGeracaoComercialParaExportacaoInternacionalService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsGeracaoComercialParaExportacaoInternacionalService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
