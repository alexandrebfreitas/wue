import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosHidraulicosReservatorioBaseDiariaResolve from './route/ons-dados-hidraulicos-reservatorio-base-diaria-routing-resolve.service';

const onsDadosHidraulicosReservatorioBaseDiariaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-hidraulicos-reservatorio-base-diaria.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseDiariaComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-hidraulicos-reservatorio-base-diaria-detail.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent,
      ),
    resolve: {
      onsDadosHidraulicosReservatorioBaseDiaria: OnsDadosHidraulicosReservatorioBaseDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-hidraulicos-reservatorio-base-diaria-update.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent,
      ),
    resolve: {
      onsDadosHidraulicosReservatorioBaseDiaria: OnsDadosHidraulicosReservatorioBaseDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-hidraulicos-reservatorio-base-diaria-update.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent,
      ),
    resolve: {
      onsDadosHidraulicosReservatorioBaseDiaria: OnsDadosHidraulicosReservatorioBaseDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosHidraulicosReservatorioBaseDiariaRoute;
