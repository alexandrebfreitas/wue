import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosGrandezasFluviometricasResolve from './route/ons-dados-grandezas-fluviometricas-routing-resolve.service';

const onsDadosGrandezasFluviometricasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-grandezas-fluviometricas.component').then(m => m.OnsDadosGrandezasFluviometricasComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-grandezas-fluviometricas-detail.component').then(m => m.OnsDadosGrandezasFluviometricasDetailComponent),
    resolve: {
      onsDadosGrandezasFluviometricas: OnsDadosGrandezasFluviometricasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-grandezas-fluviometricas-update.component').then(m => m.OnsDadosGrandezasFluviometricasUpdateComponent),
    resolve: {
      onsDadosGrandezasFluviometricas: OnsDadosGrandezasFluviometricasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-grandezas-fluviometricas-update.component').then(m => m.OnsDadosGrandezasFluviometricasUpdateComponent),
    resolve: {
      onsDadosGrandezasFluviometricas: OnsDadosGrandezasFluviometricasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosGrandezasFluviometricasRoute;
