import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCmoSemanalResolve from './route/ons-cmo-semanal-routing-resolve.service';

const onsCmoSemanalRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-cmo-semanal.component').then(m => m.OnsCmoSemanalComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-cmo-semanal-detail.component').then(m => m.OnsCmoSemanalDetailComponent),
    resolve: {
      onsCmoSemanal: OnsCmoSemanalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-cmo-semanal-update.component').then(m => m.OnsCmoSemanalUpdateComponent),
    resolve: {
      onsCmoSemanal: OnsCmoSemanalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-cmo-semanal-update.component').then(m => m.OnsCmoSemanalUpdateComponent),
    resolve: {
      onsCmoSemanal: OnsCmoSemanalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCmoSemanalRoute;
