import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResolve from './route/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-routing-resolve.service';

const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-detail.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs:
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs:
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs:
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRoute;
