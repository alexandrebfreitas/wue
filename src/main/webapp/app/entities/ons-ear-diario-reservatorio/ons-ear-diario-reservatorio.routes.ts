import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEarDiarioReservatorioResolve from './route/ons-ear-diario-reservatorio-routing-resolve.service';

const onsEarDiarioReservatorioRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ear-diario-reservatorio.component').then(m => m.OnsEarDiarioReservatorioComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-ear-diario-reservatorio-detail.component').then(m => m.OnsEarDiarioReservatorioDetailComponent),
    resolve: {
      onsEarDiarioReservatorio: OnsEarDiarioReservatorioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-ear-diario-reservatorio-update.component').then(m => m.OnsEarDiarioReservatorioUpdateComponent),
    resolve: {
      onsEarDiarioReservatorio: OnsEarDiarioReservatorioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-ear-diario-reservatorio-update.component').then(m => m.OnsEarDiarioReservatorioUpdateComponent),
    resolve: {
      onsEarDiarioReservatorio: OnsEarDiarioReservatorioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEarDiarioReservatorioRoute;
