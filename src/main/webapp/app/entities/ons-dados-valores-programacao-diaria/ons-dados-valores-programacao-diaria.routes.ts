import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosValoresProgramacaoDiariaResolve from './route/ons-dados-valores-programacao-diaria-routing-resolve.service';

const onsDadosValoresProgramacaoDiariaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-valores-programacao-diaria.component').then(m => m.OnsDadosValoresProgramacaoDiariaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-valores-programacao-diaria-detail.component').then(m => m.OnsDadosValoresProgramacaoDiariaDetailComponent),
    resolve: {
      onsDadosValoresProgramacaoDiaria: OnsDadosValoresProgramacaoDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-valores-programacao-diaria-update.component').then(m => m.OnsDadosValoresProgramacaoDiariaUpdateComponent),
    resolve: {
      onsDadosValoresProgramacaoDiaria: OnsDadosValoresProgramacaoDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-valores-programacao-diaria-update.component').then(m => m.OnsDadosValoresProgramacaoDiariaUpdateComponent),
    resolve: {
      onsDadosValoresProgramacaoDiaria: OnsDadosValoresProgramacaoDiariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosValoresProgramacaoDiariaRoute;
