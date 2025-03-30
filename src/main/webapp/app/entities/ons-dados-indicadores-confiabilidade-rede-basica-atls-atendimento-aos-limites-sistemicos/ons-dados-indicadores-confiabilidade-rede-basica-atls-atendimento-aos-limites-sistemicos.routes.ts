import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResolve from './route/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-routing-resolve.service';

const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-detail.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosDetailComponent,
      ),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-update.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent,
      ),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-update.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent,
      ),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRoute;
