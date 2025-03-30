import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDescontinuadoPrecipitacaoDiariaObservadaResolve from './route/ons-descontinuado-precipitacao-diaria-observada-routing-resolve.service';

const onsDescontinuadoPrecipitacaoDiariaObservadaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-descontinuado-precipitacao-diaria-observada.component').then(
        m => m.OnsDescontinuadoPrecipitacaoDiariaObservadaComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-descontinuado-precipitacao-diaria-observada-detail.component').then(
        m => m.OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent,
      ),
    resolve: {
      onsDescontinuadoPrecipitacaoDiariaObservada: OnsDescontinuadoPrecipitacaoDiariaObservadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-descontinuado-precipitacao-diaria-observada-update.component').then(
        m => m.OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent,
      ),
    resolve: {
      onsDescontinuadoPrecipitacaoDiariaObservada: OnsDescontinuadoPrecipitacaoDiariaObservadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-descontinuado-precipitacao-diaria-observada-update.component').then(
        m => m.OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent,
      ),
    resolve: {
      onsDescontinuadoPrecipitacaoDiariaObservada: OnsDescontinuadoPrecipitacaoDiariaObservadaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDescontinuadoPrecipitacaoDiariaObservadaRoute;
