import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsEnaDiarioReeReservatorioEquivalenteEnergiaResolve from './route/ons-ena-diario-ree-reservatorio-equivalente-energia-routing-resolve.service';

const onsEnaDiarioReeReservatorioEquivalenteEnergiaRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-ena-diario-ree-reservatorio-equivalente-energia.component').then(
        m => m.OnsEnaDiarioReeReservatorioEquivalenteEnergiaComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-ena-diario-ree-reservatorio-equivalente-energia-detail.component').then(
        m => m.OnsEnaDiarioReeReservatorioEquivalenteEnergiaDetailComponent,
      ),
    resolve: {
      onsEnaDiarioReeReservatorioEquivalenteEnergia: OnsEnaDiarioReeReservatorioEquivalenteEnergiaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-ena-diario-ree-reservatorio-equivalente-energia-update.component').then(
        m => m.OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent,
      ),
    resolve: {
      onsEnaDiarioReeReservatorioEquivalenteEnergia: OnsEnaDiarioReeReservatorioEquivalenteEnergiaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-ena-diario-ree-reservatorio-equivalente-energia-update.component').then(
        m => m.OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent,
      ),
    resolve: {
      onsEnaDiarioReeReservatorioEquivalenteEnergia: OnsEnaDiarioReeReservatorioEquivalenteEnergiaResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsEnaDiarioReeReservatorioEquivalenteEnergiaRoute;
