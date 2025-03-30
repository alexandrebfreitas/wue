import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIntercambiosEntreSubsistemasResolve from './route/ons-intercambios-entre-subsistemas-routing-resolve.service';

const onsIntercambiosEntreSubsistemasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-intercambios-entre-subsistemas.component').then(m => m.OnsIntercambiosEntreSubsistemasComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-intercambios-entre-subsistemas-detail.component').then(m => m.OnsIntercambiosEntreSubsistemasDetailComponent),
    resolve: {
      onsIntercambiosEntreSubsistemas: OnsIntercambiosEntreSubsistemasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-intercambios-entre-subsistemas-update.component').then(m => m.OnsIntercambiosEntreSubsistemasUpdateComponent),
    resolve: {
      onsIntercambiosEntreSubsistemas: OnsIntercambiosEntreSubsistemasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-intercambios-entre-subsistemas-update.component').then(m => m.OnsIntercambiosEntreSubsistemasUpdateComponent),
    resolve: {
      onsIntercambiosEntreSubsistemas: OnsIntercambiosEntreSubsistemasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIntercambiosEntreSubsistemasRoute;
