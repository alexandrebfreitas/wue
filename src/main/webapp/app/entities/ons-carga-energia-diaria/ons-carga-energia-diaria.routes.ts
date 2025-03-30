import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCargaEnergiaDiariaResolve from './route/ons-carga-energia-diaria-routing-resolve.service';

const onsCargaEnergiaDiariaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-carga-energia-diaria.component').then(m => m.OnsCargaEnergiaDiariaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-carga-energia-diaria-detail.component').then(m => m.OnsCargaEnergiaDiariaDetailComponent),
    resolve: {
      onsCargaEnergiaDiaria: OnsCargaEnergiaDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-carga-energia-diaria-update.component').then(m => m.OnsCargaEnergiaDiariaUpdateComponent),
    resolve: {
      onsCargaEnergiaDiaria: OnsCargaEnergiaDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-carga-energia-diaria-update.component').then(m => m.OnsCargaEnergiaDiariaUpdateComponent),
    resolve: {
      onsCargaEnergiaDiaria: OnsCargaEnergiaDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCargaEnergiaDiariaRoute;
