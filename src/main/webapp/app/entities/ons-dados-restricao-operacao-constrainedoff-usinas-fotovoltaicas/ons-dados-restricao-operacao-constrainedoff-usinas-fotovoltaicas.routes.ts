import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve from './route/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-routing-resolve.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detail.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRoute;
