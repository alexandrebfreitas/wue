import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCvuUsinaTermicasResolve from './route/ons-cvu-usina-termicas-routing-resolve.service';

const onsCvuUsinaTermicasRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-cvu-usina-termicas.component').then(m => m.OnsCvuUsinaTermicasComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-cvu-usina-termicas-detail.component').then(m => m.OnsCvuUsinaTermicasDetailComponent),
    resolve: {
      onsCvuUsinaTermicas: OnsCvuUsinaTermicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-cvu-usina-termicas-update.component').then(m => m.OnsCvuUsinaTermicasUpdateComponent),
    resolve: {
      onsCvuUsinaTermicas: OnsCvuUsinaTermicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-cvu-usina-termicas-update.component').then(m => m.OnsCvuUsinaTermicasUpdateComponent),
    resolve: {
      onsCvuUsinaTermicas: OnsCvuUsinaTermicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCvuUsinaTermicasRoute;
