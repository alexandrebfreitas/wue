import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsGeracaoUsinaEmBaseHorariaResolve from './route/ons-geracao-usina-em-base-horaria-routing-resolve.service';

const onsGeracaoUsinaEmBaseHorariaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-geracao-usina-em-base-horaria.component').then(m => m.OnsGeracaoUsinaEmBaseHorariaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-geracao-usina-em-base-horaria-detail.component').then(m => m.OnsGeracaoUsinaEmBaseHorariaDetailComponent),
    resolve: {
      onsGeracaoUsinaEmBaseHoraria: OnsGeracaoUsinaEmBaseHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-geracao-usina-em-base-horaria-update.component').then(m => m.OnsGeracaoUsinaEmBaseHorariaUpdateComponent),
    resolve: {
      onsGeracaoUsinaEmBaseHoraria: OnsGeracaoUsinaEmBaseHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-geracao-usina-em-base-horaria-update.component').then(m => m.OnsGeracaoUsinaEmBaseHorariaUpdateComponent),
    resolve: {
      onsGeracaoUsinaEmBaseHoraria: OnsGeracaoUsinaEmBaseHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsGeracaoUsinaEmBaseHorariaRoute;
