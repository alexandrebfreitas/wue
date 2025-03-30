import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEnaDiarioBaciaResolve from './route/ons-ena-diario-bacia-routing-resolve.service';

const onsEnaDiarioBaciaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ena-diario-bacia.component').then(m => m.OnsEnaDiarioBaciaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-ena-diario-bacia-detail.component').then(m => m.OnsEnaDiarioBaciaDetailComponent),
    resolve: {
      onsEnaDiarioBacia: OnsEnaDiarioBaciaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-ena-diario-bacia-update.component').then(m => m.OnsEnaDiarioBaciaUpdateComponent),
    resolve: {
      onsEnaDiarioBacia: OnsEnaDiarioBaciaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-ena-diario-bacia-update.component').then(m => m.OnsEnaDiarioBaciaUpdateComponent),
    resolve: {
      onsEnaDiarioBacia: OnsEnaDiarioBaciaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEnaDiarioBaciaRoute;
