import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve from './route/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-routing-resolve.service';

const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-detail.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesDetailComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes:
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes:
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes:
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRoute;
