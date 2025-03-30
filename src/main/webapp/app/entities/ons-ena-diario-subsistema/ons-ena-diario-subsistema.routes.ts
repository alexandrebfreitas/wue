import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEnaDiarioSubsistemaResolve from './route/ons-ena-diario-subsistema-routing-resolve.service';

const onsEnaDiarioSubsistemaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ena-diario-subsistema.component').then(m => m.OnsEnaDiarioSubsistemaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-ena-diario-subsistema-detail.component').then(m => m.OnsEnaDiarioSubsistemaDetailComponent),
    resolve: {
      onsEnaDiarioSubsistema: OnsEnaDiarioSubsistemaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-ena-diario-subsistema-update.component').then(m => m.OnsEnaDiarioSubsistemaUpdateComponent),
    resolve: {
      onsEnaDiarioSubsistema: OnsEnaDiarioSubsistemaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-ena-diario-subsistema-update.component').then(m => m.OnsEnaDiarioSubsistemaUpdateComponent),
    resolve: {
      onsEnaDiarioSubsistema: OnsEnaDiarioSubsistemaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEnaDiarioSubsistemaRoute;
