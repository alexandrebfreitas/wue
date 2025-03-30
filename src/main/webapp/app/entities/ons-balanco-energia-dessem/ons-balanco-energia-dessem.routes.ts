import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsBalancoEnergiaDessemResolve from './route/ons-balanco-energia-dessem-routing-resolve.service';

const onsBalancoEnergiaDessemRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-balanco-energia-dessem.component').then(m => m.OnsBalancoEnergiaDessemComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-balanco-energia-dessem-detail.component').then(m => m.OnsBalancoEnergiaDessemDetailComponent),
    resolve: {
      onsBalancoEnergiaDessem: OnsBalancoEnergiaDessemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-balanco-energia-dessem-update.component').then(m => m.OnsBalancoEnergiaDessemUpdateComponent),
    resolve: {
      onsBalancoEnergiaDessem: OnsBalancoEnergiaDessemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-balanco-energia-dessem-update.component').then(m => m.OnsBalancoEnergiaDessemUpdateComponent),
    resolve: {
      onsBalancoEnergiaDessem: OnsBalancoEnergiaDessemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsBalancoEnergiaDessemRoute;
