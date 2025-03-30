import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEquipamentosControleReativosResolve from './route/ons-equipamentos-controle-reativos-routing-resolve.service';

const onsEquipamentosControleReativosRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-equipamentos-controle-reativos.component').then(m => m.OnsEquipamentosControleReativosComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-equipamentos-controle-reativos-detail.component').then(m => m.OnsEquipamentosControleReativosDetailComponent),
    resolve: {
      onsEquipamentosControleReativos: OnsEquipamentosControleReativosResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-equipamentos-controle-reativos-update.component').then(m => m.OnsEquipamentosControleReativosUpdateComponent),
    resolve: {
      onsEquipamentosControleReativos: OnsEquipamentosControleReativosResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-equipamentos-controle-reativos-update.component').then(m => m.OnsEquipamentosControleReativosUpdateComponent),
    resolve: {
      onsEquipamentosControleReativos: OnsEquipamentosControleReativosResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEquipamentosControleReativosRoute;
