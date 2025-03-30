import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResolve from './route/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-routing-resolve.service';

const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-detail.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRoute;
