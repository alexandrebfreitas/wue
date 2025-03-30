import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCargaEnergiaMensalResolve from './route/ons-carga-energia-mensal-routing-resolve.service';

const onsCargaEnergiaMensalRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-carga-energia-mensal.component').then(m => m.OnsCargaEnergiaMensalComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-carga-energia-mensal-detail.component').then(m => m.OnsCargaEnergiaMensalDetailComponent),
    resolve: {
      onsCargaEnergiaMensal: OnsCargaEnergiaMensalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-carga-energia-mensal-update.component').then(m => m.OnsCargaEnergiaMensalUpdateComponent),
    resolve: {
      onsCargaEnergiaMensal: OnsCargaEnergiaMensalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-carga-energia-mensal-update.component').then(m => m.OnsCargaEnergiaMensalUpdateComponent),
    resolve: {
      onsCargaEnergiaMensal: OnsCargaEnergiaMensalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCargaEnergiaMensalRoute;
