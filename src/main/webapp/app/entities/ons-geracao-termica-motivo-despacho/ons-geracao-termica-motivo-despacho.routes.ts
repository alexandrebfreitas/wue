import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsGeracaoTermicaMotivoDespachoResolve from './route/ons-geracao-termica-motivo-despacho-routing-resolve.service';

const onsGeracaoTermicaMotivoDespachoRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-geracao-termica-motivo-despacho.component').then(m => m.OnsGeracaoTermicaMotivoDespachoComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-geracao-termica-motivo-despacho-detail.component').then(m => m.OnsGeracaoTermicaMotivoDespachoDetailComponent),
    resolve: {
      onsGeracaoTermicaMotivoDespacho: OnsGeracaoTermicaMotivoDespachoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-geracao-termica-motivo-despacho-update.component').then(m => m.OnsGeracaoTermicaMotivoDespachoUpdateComponent),
    resolve: {
      onsGeracaoTermicaMotivoDespacho: OnsGeracaoTermicaMotivoDespachoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-geracao-termica-motivo-despacho-update.component').then(m => m.OnsGeracaoTermicaMotivoDespachoUpdateComponent),
    resolve: {
      onsGeracaoTermicaMotivoDespacho: OnsGeracaoTermicaMotivoDespachoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsGeracaoTermicaMotivoDespachoRoute;
