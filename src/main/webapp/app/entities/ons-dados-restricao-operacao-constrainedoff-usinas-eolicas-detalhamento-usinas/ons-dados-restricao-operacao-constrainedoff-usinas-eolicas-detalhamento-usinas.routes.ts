import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve from './route/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-routing-resolve.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-detail.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas:
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas:
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas:
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRoute;
