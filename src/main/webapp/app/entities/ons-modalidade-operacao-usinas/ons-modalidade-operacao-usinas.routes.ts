import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsModalidadeOperacaoUsinasResolve from './route/ons-modalidade-operacao-usinas-routing-resolve.service';

const onsModalidadeOperacaoUsinasRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/ons-modalidade-operacao-usinas.component').then(m => m.OnsModalidadeOperacaoUsinasComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-modalidade-operacao-usinas-detail.component').then(m => m.OnsModalidadeOperacaoUsinasDetailComponent),
    resolve: {
      onsModalidadeOperacaoUsinas: OnsModalidadeOperacaoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-modalidade-operacao-usinas-update.component').then(m => m.OnsModalidadeOperacaoUsinasUpdateComponent),
    resolve: {
      onsModalidadeOperacaoUsinas: OnsModalidadeOperacaoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-modalidade-operacao-usinas-update.component').then(m => m.OnsModalidadeOperacaoUsinasUpdateComponent),
    resolve: {
      onsModalidadeOperacaoUsinas: OnsModalidadeOperacaoUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsModalidadeOperacaoUsinasRoute;
