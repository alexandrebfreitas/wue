import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoResolve from './route/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-routing-resolve.service';

const onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.component').then(
        m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import(
        './detail/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-detail.component'
      ).then(m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoDetailComponent),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import(
        './update/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-update.component'
      ).then(m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoUpdateComponent),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import(
        './update/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao-update.component'
      ).then(m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoUpdateComponent),
    resolve: {
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao:
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoRoute;
