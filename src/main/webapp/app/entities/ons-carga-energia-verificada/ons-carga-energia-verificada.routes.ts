import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCargaEnergiaVerificadaResolve from './route/ons-carga-energia-verificada-routing-resolve.service';

const onsCargaEnergiaVerificadaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-carga-energia-verificada.component').then(m => m.OnsCargaEnergiaVerificadaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-carga-energia-verificada-detail.component').then(m => m.OnsCargaEnergiaVerificadaDetailComponent),
    resolve: {
      onsCargaEnergiaVerificada: OnsCargaEnergiaVerificadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-carga-energia-verificada-update.component').then(m => m.OnsCargaEnergiaVerificadaUpdateComponent),
    resolve: {
      onsCargaEnergiaVerificada: OnsCargaEnergiaVerificadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-carga-energia-verificada-update.component').then(m => m.OnsCargaEnergiaVerificadaUpdateComponent),
    resolve: {
      onsCargaEnergiaVerificada: OnsCargaEnergiaVerificadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCargaEnergiaVerificadaRoute;
