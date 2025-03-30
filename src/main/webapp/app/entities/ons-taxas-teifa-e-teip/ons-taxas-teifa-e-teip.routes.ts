import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsTaxasTeifaETeipResolve from './route/ons-taxas-teifa-e-teip-routing-resolve.service';

const onsTaxasTeifaETeipRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-taxas-teifa-e-teip.component').then(m => m.OnsTaxasTeifaETeipComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-taxas-teifa-e-teip-detail.component').then(m => m.OnsTaxasTeifaETeipDetailComponent),
    resolve: {
      onsTaxasTeifaETeip: OnsTaxasTeifaETeipResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-taxas-teifa-e-teip-update.component').then(m => m.OnsTaxasTeifaETeipUpdateComponent),
    resolve: {
      onsTaxasTeifaETeip: OnsTaxasTeifaETeipResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-taxas-teifa-e-teip-update.component').then(m => m.OnsTaxasTeifaETeipUpdateComponent),
    resolve: {
      onsTaxasTeifaETeip: OnsTaxasTeifaETeipResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsTaxasTeifaETeipRoute;
