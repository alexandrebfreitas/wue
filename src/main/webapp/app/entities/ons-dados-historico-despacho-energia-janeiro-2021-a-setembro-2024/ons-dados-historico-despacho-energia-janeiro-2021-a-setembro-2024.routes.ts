import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve from './route/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-routing-resolve.service';

const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Route: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.component').then(
        m => m.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-detail.component').then(
        m => m.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent,
      ),
    resolve: {
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-update.component').then(
        m => m.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent,
      ),
    resolve: {
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-update.component').then(
        m => m.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent,
      ),
    resolve: {
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Route;
