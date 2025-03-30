import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosHidrologicosVolumeEsperaRecomendadoResolve from './route/ons-dados-hidrologicos-volume-espera-recomendado-routing-resolve.service';

const onsDadosHidrologicosVolumeEsperaRecomendadoRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-hidrologicos-volume-espera-recomendado.component').then(
        m => m.OnsDadosHidrologicosVolumeEsperaRecomendadoComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-hidrologicos-volume-espera-recomendado-detail.component').then(
        m => m.OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent,
      ),
    resolve: {
      onsDadosHidrologicosVolumeEsperaRecomendado: OnsDadosHidrologicosVolumeEsperaRecomendadoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-hidrologicos-volume-espera-recomendado-update.component').then(
        m => m.OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent,
      ),
    resolve: {
      onsDadosHidrologicosVolumeEsperaRecomendado: OnsDadosHidrologicosVolumeEsperaRecomendadoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-hidrologicos-volume-espera-recomendado-update.component').then(
        m => m.OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent,
      ),
    resolve: {
      onsDadosHidrologicosVolumeEsperaRecomendado: OnsDadosHidrologicosVolumeEsperaRecomendadoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosHidrologicosVolumeEsperaRecomendadoRoute;
