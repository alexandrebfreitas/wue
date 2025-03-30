import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosProgramadosElementosFluxoControladoResolve from './route/ons-dados-programados-elementos-fluxo-controlado-routing-resolve.service';

const onsDadosProgramadosElementosFluxoControladoRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-programados-elementos-fluxo-controlado.component').then(
        m => m.OnsDadosProgramadosElementosFluxoControladoComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-programados-elementos-fluxo-controlado-detail.component').then(
        m => m.OnsDadosProgramadosElementosFluxoControladoDetailComponent,
      ),
    resolve: {
      onsDadosProgramadosElementosFluxoControlado: OnsDadosProgramadosElementosFluxoControladoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-programados-elementos-fluxo-controlado-update.component').then(
        m => m.OnsDadosProgramadosElementosFluxoControladoUpdateComponent,
      ),
    resolve: {
      onsDadosProgramadosElementosFluxoControlado: OnsDadosProgramadosElementosFluxoControladoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-programados-elementos-fluxo-controlado-update.component').then(
        m => m.OnsDadosProgramadosElementosFluxoControladoUpdateComponent,
      ),
    resolve: {
      onsDadosProgramadosElementosFluxoControlado: OnsDadosProgramadosElementosFluxoControladoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosProgramadosElementosFluxoControladoRoute;
