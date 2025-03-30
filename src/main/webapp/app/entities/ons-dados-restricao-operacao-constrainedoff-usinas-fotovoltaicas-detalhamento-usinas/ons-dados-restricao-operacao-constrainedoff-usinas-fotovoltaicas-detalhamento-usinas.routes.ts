import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResolve from './route/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-routing-resolve.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-detail.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDetailComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas:
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas:
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas:
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRoute;
