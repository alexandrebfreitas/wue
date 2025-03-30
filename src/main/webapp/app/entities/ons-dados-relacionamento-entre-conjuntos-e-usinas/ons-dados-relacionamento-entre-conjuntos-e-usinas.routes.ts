import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosRelacionamentoEntreConjuntosEUsinasResolve from './route/ons-dados-relacionamento-entre-conjuntos-e-usinas-routing-resolve.service';

const onsDadosRelacionamentoEntreConjuntosEUsinasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-relacionamento-entre-conjuntos-e-usinas.component').then(
        m => m.OnsDadosRelacionamentoEntreConjuntosEUsinasComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-relacionamento-entre-conjuntos-e-usinas-detail.component').then(
        m => m.OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent,
      ),
    resolve: {
      onsDadosRelacionamentoEntreConjuntosEUsinas: OnsDadosRelacionamentoEntreConjuntosEUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-relacionamento-entre-conjuntos-e-usinas-update.component').then(
        m => m.OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRelacionamentoEntreConjuntosEUsinas: OnsDadosRelacionamentoEntreConjuntosEUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-relacionamento-entre-conjuntos-e-usinas-update.component').then(
        m => m.OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRelacionamentoEntreConjuntosEUsinas: OnsDadosRelacionamentoEntreConjuntosEUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosRelacionamentoEntreConjuntosEUsinasRoute;
