import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsFatorCapacidadeGeracaoEolicaESolarResolve from './route/ons-fator-capacidade-geracao-eolica-e-solar-routing-resolve.service';

const onsFatorCapacidadeGeracaoEolicaESolarRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-fator-capacidade-geracao-eolica-e-solar.component').then(m => m.OnsFatorCapacidadeGeracaoEolicaESolarComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-fator-capacidade-geracao-eolica-e-solar-detail.component').then(
        m => m.OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent,
      ),
    resolve: {
      onsFatorCapacidadeGeracaoEolicaESolar: OnsFatorCapacidadeGeracaoEolicaESolarResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-fator-capacidade-geracao-eolica-e-solar-update.component').then(
        m => m.OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent,
      ),
    resolve: {
      onsFatorCapacidadeGeracaoEolicaESolar: OnsFatorCapacidadeGeracaoEolicaESolarResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-fator-capacidade-geracao-eolica-e-solar-update.component').then(
        m => m.OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent,
      ),
    resolve: {
      onsFatorCapacidadeGeracaoEolicaESolar: OnsFatorCapacidadeGeracaoEolicaESolarResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsFatorCapacidadeGeracaoEolicaESolarRoute;
