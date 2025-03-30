import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCargaEnergiaProgramadaResolve from './route/ons-carga-energia-programada-routing-resolve.service';

const onsCargaEnergiaProgramadaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-carga-energia-programada.component').then(m => m.OnsCargaEnergiaProgramadaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-carga-energia-programada-detail.component').then(m => m.OnsCargaEnergiaProgramadaDetailComponent),
    resolve: {
      onsCargaEnergiaProgramada: OnsCargaEnergiaProgramadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-carga-energia-programada-update.component').then(m => m.OnsCargaEnergiaProgramadaUpdateComponent),
    resolve: {
      onsCargaEnergiaProgramada: OnsCargaEnergiaProgramadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-carga-energia-programada-update.component').then(m => m.OnsCargaEnergiaProgramadaUpdateComponent),
    resolve: {
      onsCargaEnergiaProgramada: OnsCargaEnergiaProgramadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCargaEnergiaProgramadaRoute;
