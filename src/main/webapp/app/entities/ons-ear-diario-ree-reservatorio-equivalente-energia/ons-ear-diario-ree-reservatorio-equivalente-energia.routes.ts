import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEarDiarioReeReservatorioEquivalenteEnergiaResolve from './route/ons-ear-diario-ree-reservatorio-equivalente-energia-routing-resolve.service';

const onsEarDiarioReeReservatorioEquivalenteEnergiaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-ear-diario-ree-reservatorio-equivalente-energia.component').then(
        m => m.OnsEarDiarioReeReservatorioEquivalenteEnergiaComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-ear-diario-ree-reservatorio-equivalente-energia-detail.component').then(
        m => m.OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent,
      ),
    resolve: {
      onsEarDiarioReeReservatorioEquivalenteEnergia: OnsEarDiarioReeReservatorioEquivalenteEnergiaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-ear-diario-ree-reservatorio-equivalente-energia-update.component').then(
        m => m.OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent,
      ),
    resolve: {
      onsEarDiarioReeReservatorioEquivalenteEnergia: OnsEarDiarioReeReservatorioEquivalenteEnergiaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-ear-diario-ree-reservatorio-equivalente-energia-update.component').then(
        m => m.OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent,
      ),
    resolve: {
      onsEarDiarioReeReservatorioEquivalenteEnergia: OnsEarDiarioReeReservatorioEquivalenteEnergiaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEarDiarioReeReservatorioEquivalenteEnergiaRoute;
