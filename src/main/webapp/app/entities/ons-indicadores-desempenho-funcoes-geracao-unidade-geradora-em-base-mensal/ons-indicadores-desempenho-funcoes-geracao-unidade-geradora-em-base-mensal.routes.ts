import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve from './route/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-routing-resolve.service';

const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-detail.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent,
      ),
    resolve: {
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal:
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-update.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent,
      ),
    resolve: {
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal:
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-update.component').then(
        m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent,
      ),
    resolve: {
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal:
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRoute;
