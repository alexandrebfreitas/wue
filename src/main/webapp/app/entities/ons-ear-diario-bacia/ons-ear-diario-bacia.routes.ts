import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEarDiarioBaciaResolve from './route/ons-ear-diario-bacia-routing-resolve.service';

const onsEarDiarioBaciaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ear-diario-bacia.component').then(m => m.OnsEarDiarioBaciaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-ear-diario-bacia-detail.component').then(m => m.OnsEarDiarioBaciaDetailComponent),
    resolve: {
      onsEarDiarioBacia: OnsEarDiarioBaciaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-ear-diario-bacia-update.component').then(m => m.OnsEarDiarioBaciaUpdateComponent),
    resolve: {
      onsEarDiarioBacia: OnsEarDiarioBaciaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-ear-diario-bacia-update.component').then(m => m.OnsEarDiarioBaciaUpdateComponent),
    resolve: {
      onsEarDiarioBacia: OnsEarDiarioBaciaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEarDiarioBaciaRoute;
