import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve from './route/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-routing-resolve.service';

const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.component').then(
        m => m.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-detail.component').then(
        m => m.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent,
      ),
    resolve: {
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-update.component').then(
        m => m.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-update.component').then(
        m => m.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent,
      ),
    resolve: {
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRoute;
