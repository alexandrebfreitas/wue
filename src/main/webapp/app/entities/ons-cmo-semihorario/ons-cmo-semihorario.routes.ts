import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCmoSemihorarioResolve from './route/ons-cmo-semihorario-routing-resolve.service';

const onsCmoSemihorarioRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-cmo-semihorario.component').then(m => m.OnsCmoSemihorarioComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-cmo-semihorario-detail.component').then(m => m.OnsCmoSemihorarioDetailComponent),
    resolve: {
      onsCmoSemihorario: OnsCmoSemihorarioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-cmo-semihorario-update.component').then(m => m.OnsCmoSemihorarioUpdateComponent),
    resolve: {
      onsCmoSemihorario: OnsCmoSemihorarioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-cmo-semihorario-update.component').then(m => m.OnsCmoSemihorarioUpdateComponent),
    resolve: {
      onsCmoSemihorario: OnsCmoSemihorarioResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCmoSemihorarioRoute;
