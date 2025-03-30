import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsOfertasPrecoParaImportacaoResolve from './route/ons-ofertas-preco-para-importacao-routing-resolve.service';

const onsOfertasPrecoParaImportacaoRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-ofertas-preco-para-importacao.component').then(m => m.OnsOfertasPrecoParaImportacaoComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-ofertas-preco-para-importacao-detail.component').then(m => m.OnsOfertasPrecoParaImportacaoDetailComponent),
    resolve: {
      onsOfertasPrecoParaImportacao: OnsOfertasPrecoParaImportacaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-ofertas-preco-para-importacao-update.component').then(m => m.OnsOfertasPrecoParaImportacaoUpdateComponent),
    resolve: {
      onsOfertasPrecoParaImportacao: OnsOfertasPrecoParaImportacaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-ofertas-preco-para-importacao-update.component').then(m => m.OnsOfertasPrecoParaImportacaoUpdateComponent),
    resolve: {
      onsOfertasPrecoParaImportacao: OnsOfertasPrecoParaImportacaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsOfertasPrecoParaImportacaoRoute;
