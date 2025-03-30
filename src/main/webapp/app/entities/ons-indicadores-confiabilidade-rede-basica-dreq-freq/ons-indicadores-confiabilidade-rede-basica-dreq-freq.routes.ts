import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve from './route/ons-indicadores-confiabilidade-rede-basica-dreq-freq-routing-resolve.service';

const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-indicadores-confiabilidade-rede-basica-dreq-freq.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-indicadores-confiabilidade-rede-basica-dreq-freq-detail.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-dreq-freq-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-indicadores-confiabilidade-rede-basica-dreq-freq-update.component').then(
        m => m.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent,
      ),
    resolve: {
      onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRoute;
