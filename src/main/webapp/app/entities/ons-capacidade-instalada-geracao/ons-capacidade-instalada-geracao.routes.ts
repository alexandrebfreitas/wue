import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCapacidadeInstaladaGeracaoResolve from './route/ons-capacidade-instalada-geracao-routing-resolve.service';

const onsCapacidadeInstaladaGeracaoRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-capacidade-instalada-geracao.component').then(m => m.OnsCapacidadeInstaladaGeracaoComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-capacidade-instalada-geracao-detail.component').then(m => m.OnsCapacidadeInstaladaGeracaoDetailComponent),
    resolve: {
      onsCapacidadeInstaladaGeracao: OnsCapacidadeInstaladaGeracaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-capacidade-instalada-geracao-update.component').then(m => m.OnsCapacidadeInstaladaGeracaoUpdateComponent),
    resolve: {
      onsCapacidadeInstaladaGeracao: OnsCapacidadeInstaladaGeracaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-capacidade-instalada-geracao-update.component').then(m => m.OnsCapacidadeInstaladaGeracaoUpdateComponent),
    resolve: {
      onsCapacidadeInstaladaGeracao: OnsCapacidadeInstaladaGeracaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCapacidadeInstaladaGeracaoRoute;
