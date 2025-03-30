import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve from './route/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-routing-resolve.service';

const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-detail.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent,
      ),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-update.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent,
      ),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-update.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent,
      ),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRoute;
