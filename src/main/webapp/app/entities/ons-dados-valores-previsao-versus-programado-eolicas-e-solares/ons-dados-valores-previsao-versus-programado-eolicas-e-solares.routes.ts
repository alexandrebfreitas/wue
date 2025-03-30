import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve from './route/ons-dados-valores-previsao-versus-programado-eolicas-e-solares-routing-resolve.service';

const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.component').then(
        m => m.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-valores-previsao-versus-programado-eolicas-e-solares-detail.component').then(
        m => m.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresDetailComponent,
      ),
    resolve: {
      onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-valores-previsao-versus-programado-eolicas-e-solares-update.component').then(
        m => m.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent,
      ),
    resolve: {
      onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-valores-previsao-versus-programado-eolicas-e-solares-update.component').then(
        m => m.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent,
      ),
    resolve: {
      onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRoute;
