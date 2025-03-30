import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResolve from './route/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-routing-resolve.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detail.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-update.component').then(
        m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent,
      ),
    resolve: {
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRoute;
