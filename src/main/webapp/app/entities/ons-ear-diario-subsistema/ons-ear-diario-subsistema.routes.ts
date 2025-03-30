import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEarDiarioSubsistemaResolve from './route/ons-ear-diario-subsistema-routing-resolve.service';

const onsEarDiarioSubsistemaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ear-diario-subsistema.component').then(m => m.OnsEarDiarioSubsistemaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-ear-diario-subsistema-detail.component').then(m => m.OnsEarDiarioSubsistemaDetailComponent),
    resolve: {
      onsEarDiarioSubsistema: OnsEarDiarioSubsistemaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-ear-diario-subsistema-update.component').then(m => m.OnsEarDiarioSubsistemaUpdateComponent),
    resolve: {
      onsEarDiarioSubsistema: OnsEarDiarioSubsistemaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-ear-diario-subsistema-update.component').then(m => m.OnsEarDiarioSubsistemaUpdateComponent),
    resolve: {
      onsEarDiarioSubsistema: OnsEarDiarioSubsistemaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEarDiarioSubsistemaRoute;
