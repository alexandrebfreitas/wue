import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosDisponibilidadeUsinasResolve from './route/ons-dados-disponibilidade-usinas-routing-resolve.service';

const onsDadosDisponibilidadeUsinasRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-dados-disponibilidade-usinas.component').then(m => m.OnsDadosDisponibilidadeUsinasComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-disponibilidade-usinas-detail.component').then(m => m.OnsDadosDisponibilidadeUsinasDetailComponent),
    resolve: {
      onsDadosDisponibilidadeUsinas: OnsDadosDisponibilidadeUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-disponibilidade-usinas-update.component').then(m => m.OnsDadosDisponibilidadeUsinasUpdateComponent),
    resolve: {
      onsDadosDisponibilidadeUsinas: OnsDadosDisponibilidadeUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-disponibilidade-usinas-update.component').then(m => m.OnsDadosDisponibilidadeUsinasUpdateComponent),
    resolve: {
      onsDadosDisponibilidadeUsinas: OnsDadosDisponibilidadeUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosDisponibilidadeUsinasRoute;
