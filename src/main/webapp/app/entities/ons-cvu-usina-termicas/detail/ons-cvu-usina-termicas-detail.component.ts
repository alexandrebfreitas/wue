import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';

@Component({
  selector: 'jhi-ons-cvu-usina-termicas-detail',
  templateUrl: './ons-cvu-usina-termicas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsCvuUsinaTermicasDetailComponent {
  onsCvuUsinaTermicas = input<IOnsCvuUsinaTermicas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
