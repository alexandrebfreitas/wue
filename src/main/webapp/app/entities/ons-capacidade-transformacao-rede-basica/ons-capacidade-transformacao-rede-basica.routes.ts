import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsCapacidadeTransformacaoRedeBasicaResolve from './route/ons-capacidade-transformacao-rede-basica-routing-resolve.service';

const onsCapacidadeTransformacaoRedeBasicaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-capacidade-transformacao-rede-basica.component').then(m => m.OnsCapacidadeTransformacaoRedeBasicaComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-capacidade-transformacao-rede-basica-detail.component').then(
        m => m.OnsCapacidadeTransformacaoRedeBasicaDetailComponent,
      ),
    resolve: {
      onsCapacidadeTransformacaoRedeBasica: OnsCapacidadeTransformacaoRedeBasicaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-capacidade-transformacao-rede-basica-update.component').then(
        m => m.OnsCapacidadeTransformacaoRedeBasicaUpdateComponent,
      ),
    resolve: {
      onsCapacidadeTransformacaoRedeBasica: OnsCapacidadeTransformacaoRedeBasicaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-capacidade-transformacao-rede-basica-update.component').then(
        m => m.OnsCapacidadeTransformacaoRedeBasicaUpdateComponent,
      ),
    resolve: {
      onsCapacidadeTransformacaoRedeBasica: OnsCapacidadeTransformacaoRedeBasicaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsCapacidadeTransformacaoRedeBasicaRoute;
