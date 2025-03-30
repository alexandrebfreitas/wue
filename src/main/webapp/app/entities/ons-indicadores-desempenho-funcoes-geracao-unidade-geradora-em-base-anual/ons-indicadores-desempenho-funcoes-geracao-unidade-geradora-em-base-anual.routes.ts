import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve from './route/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-routing-resolve.service';

const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-detail.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent,
      ),
    resolve: {
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual:
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-update.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent,
      ),
    resolve: {
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual:
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-update.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent,
      ),
    resolve: {
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual:
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRoute;
