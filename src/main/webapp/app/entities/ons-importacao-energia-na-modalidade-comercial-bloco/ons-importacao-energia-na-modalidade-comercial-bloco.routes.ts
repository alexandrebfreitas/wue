import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsImportacaoEnergiaNaModalidadeComercialBlocoResolve from './route/ons-importacao-energia-na-modalidade-comercial-bloco-routing-resolve.service';

const onsImportacaoEnergiaNaModalidadeComercialBlocoRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-importacao-energia-na-modalidade-comercial-bloco.component').then(
        m => m.OnsImportacaoEnergiaNaModalidadeComercialBlocoComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-importacao-energia-na-modalidade-comercial-bloco-detail.component').then(
        m => m.OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent,
      ),
    resolve: {
      onsImportacaoEnergiaNaModalidadeComercialBloco: OnsImportacaoEnergiaNaModalidadeComercialBlocoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-importacao-energia-na-modalidade-comercial-bloco-update.component').then(
        m => m.OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent,
      ),
    resolve: {
      onsImportacaoEnergiaNaModalidadeComercialBloco: OnsImportacaoEnergiaNaModalidadeComercialBlocoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-importacao-energia-na-modalidade-comercial-bloco-update.component').then(
        m => m.OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent,
      ),
    resolve: {
      onsImportacaoEnergiaNaModalidadeComercialBloco: OnsImportacaoEnergiaNaModalidadeComercialBlocoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsImportacaoEnergiaNaModalidadeComercialBlocoRoute;
