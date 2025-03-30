import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosHidraulicosReservatorioBaseHorariaResolve from './route/ons-dados-hidraulicos-reservatorio-base-horaria-routing-resolve.service';

const onsDadosHidraulicosReservatorioBaseHorariaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-hidraulicos-reservatorio-base-horaria.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseHorariaComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-hidraulicos-reservatorio-base-horaria-detail.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent,
      ),
    resolve: {
      onsDadosHidraulicosReservatorioBaseHoraria: OnsDadosHidraulicosReservatorioBaseHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-hidraulicos-reservatorio-base-horaria-update.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent,
      ),
    resolve: {
      onsDadosHidraulicosReservatorioBaseHoraria: OnsDadosHidraulicosReservatorioBaseHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-hidraulicos-reservatorio-base-horaria-update.component').then(
        m => m.OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent,
      ),
    resolve: {
      onsDadosHidraulicosReservatorioBaseHoraria: OnsDadosHidraulicosReservatorioBaseHorariaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosHidraulicosReservatorioBaseHorariaRoute;
