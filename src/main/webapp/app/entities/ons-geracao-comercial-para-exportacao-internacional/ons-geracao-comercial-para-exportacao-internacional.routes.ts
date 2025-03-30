import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import OnsGeracaoComercialParaExportacaoInternacionalResolve from './route/ons-geracao-comercial-para-exportacao-internacional-routing-resolve.service';

const onsGeracaoComercialParaExportacaoInternacionalRoute: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./list/ons-geracao-comercial-para-exportacao-internacional.component').then(
        m => m.OnsGeracaoComercialParaExportacaoInternacionalComponent,
      ),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/ons-geracao-comercial-para-exportacao-internacional-detail.component').then(
        m => m.OnsGeracaoComercialParaExportacaoInternacionalDetailComponent,
      ),
    resolve: {
      onsGeracaoComercialParaExportacaoInternacional: OnsGeracaoComercialParaExportacaoInternacionalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/ons-geracao-comercial-para-exportacao-internacional-update.component').then(
        m => m.OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent,
      ),
    resolve: {
      onsGeracaoComercialParaExportacaoInternacional: OnsGeracaoComercialParaExportacaoInternacionalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/ons-geracao-comercial-para-exportacao-internacional-update.component').then(
        m => m.OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent,
      ),
    resolve: {
      onsGeracaoComercialParaExportacaoInternacional: OnsGeracaoComercialParaExportacaoInternacionalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default onsGeracaoComercialParaExportacaoInternacionalRoute;
