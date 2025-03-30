import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve from './route/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-routing-resolve.service';

const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.component').then(
        m => m.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-detail.component').then(
        m => m.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDetailComponent,
      ),
    resolve: {
      onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-update.component').then(
        m => m.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent,
      ),
    resolve: {
      onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-update.component').then(
        m => m.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent,
      ),
    resolve: {
      onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRoute;
