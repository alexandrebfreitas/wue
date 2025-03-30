import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsLinhasTransmissaoRedeOperacaoResolve from './route/ons-linhas-transmissao-rede-operacao-routing-resolve.service';

const onsLinhasTransmissaoRedeOperacaoRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-linhas-transmissao-rede-operacao.component').then(m => m.OnsLinhasTransmissaoRedeOperacaoComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-linhas-transmissao-rede-operacao-detail.component').then(m => m.OnsLinhasTransmissaoRedeOperacaoDetailComponent),
    resolve: {
      onsLinhasTransmissaoRedeOperacao: OnsLinhasTransmissaoRedeOperacaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-linhas-transmissao-rede-operacao-update.component').then(m => m.OnsLinhasTransmissaoRedeOperacaoUpdateComponent),
    resolve: {
      onsLinhasTransmissaoRedeOperacao: OnsLinhasTransmissaoRedeOperacaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-linhas-transmissao-rede-operacao-update.component').then(m => m.OnsLinhasTransmissaoRedeOperacaoUpdateComponent),
    resolve: {
      onsLinhasTransmissaoRedeOperacao: OnsLinhasTransmissaoRedeOperacaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsLinhasTransmissaoRedeOperacaoRoute;
