import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosIntercambioEnergiaModalidadeResolve from './route/ons-dados-intercambio-energia-modalidade-routing-resolve.service';

const onsDadosIntercambioEnergiaModalidadeRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-intercambio-energia-modalidade.component').then(m => m.OnsDadosIntercambioEnergiaModalidadeComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-intercambio-energia-modalidade-detail.component').then(
        m => m.OnsDadosIntercambioEnergiaModalidadeDetailComponent,
      ),
    resolve: {
      onsDadosIntercambioEnergiaModalidade: OnsDadosIntercambioEnergiaModalidadeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-intercambio-energia-modalidade-update.component').then(
        m => m.OnsDadosIntercambioEnergiaModalidadeUpdateComponent,
      ),
    resolve: {
      onsDadosIntercambioEnergiaModalidade: OnsDadosIntercambioEnergiaModalidadeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-intercambio-energia-modalidade-update.component').then(
        m => m.OnsDadosIntercambioEnergiaModalidadeUpdateComponent,
      ),
    resolve: {
      onsDadosIntercambioEnergiaModalidade: OnsDadosIntercambioEnergiaModalidadeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosIntercambioEnergiaModalidadeRoute;
