import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';

@Component({
  selector: 'jhi-ons-dados-disponibilidade-usinas-detail',
  templateUrl: './ons-dados-disponibilidade-usinas-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OnsDadosDisponibilidadeUsinasDetailComponent {
  onsDadosDisponibilidadeUsinas = input<IOnsDadosDisponibilidadeUsinas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
