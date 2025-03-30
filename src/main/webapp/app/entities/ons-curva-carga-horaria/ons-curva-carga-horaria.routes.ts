import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCurvaCargaHorariaResolve from './route/ons-curva-carga-horaria-routing-resolve.service';

const onsCurvaCargaHorariaRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-curva-carga-horaria.component').then(m => m.OnsCurvaCargaHorariaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/ons-curva-carga-horaria-detail.component').then(m => m.OnsCurvaCargaHorariaDetailComponent),
    resolve: {
      onsCurvaCargaHoraria: OnsCurvaCargaHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/ons-curva-carga-horaria-update.component').then(m => m.OnsCurvaCargaHorariaUpdateComponent),
    resolve: {
      onsCurvaCargaHoraria: OnsCurvaCargaHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/ons-curva-carga-horaria-update.component').then(m => m.OnsCurvaCargaHorariaUpdateComponent),
    resolve: {
      onsCurvaCargaHoraria: OnsCurvaCargaHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCurvaCargaHorariaRoute;
