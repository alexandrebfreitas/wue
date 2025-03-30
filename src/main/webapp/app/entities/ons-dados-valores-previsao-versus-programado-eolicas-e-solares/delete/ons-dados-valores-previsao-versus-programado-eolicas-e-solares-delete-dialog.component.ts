import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';
import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService } from '../service/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';

@Component({
  templateUrl: './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDeleteDialogComponent {
  onsDadosValoresPrevisaoVersusProgramadoEolicasESolares?: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares;

  protected onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService = inject(
    OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService,
  );
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
