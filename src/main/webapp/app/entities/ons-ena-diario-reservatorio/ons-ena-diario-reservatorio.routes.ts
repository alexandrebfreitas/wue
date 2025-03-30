import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEnaDiarioReservatorioResolve from './route/ons-ena-diario-reservatorio-routing-resolve.service';

const onsEnaDiarioReservatorioRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ena-diario-reservatorio.component').then(m => m.OnsEnaDiarioReservatorioComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-ena-diario-reservatorio-detail.component').then(m => m.OnsEnaDiarioReservatorioDetailComponent),
    resolve: {
      onsEnaDiarioReservatorio: OnsEnaDiarioReservatorioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-ena-diario-reservatorio-update.component').then(m => m.OnsEnaDiarioReservatorioUpdateComponent),
    resolve: {
      onsEnaDiarioReservatorio: OnsEnaDiarioReservatorioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-ena-diario-reservatorio-update.component').then(m => m.OnsEnaDiarioReservatorioUpdateComponent),
    resolve: {
      onsEnaDiarioReservatorio: OnsEnaDiarioReservatorioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEnaDiarioReservatorioRoute;
