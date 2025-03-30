import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';
import { OnsGeracaoComercialParaExportacaoInternacionalService } from '../service/ons-geracao-comercial-para-exportacao-internacional.service';

const onsGeracaoComercialParaExportacaoInternacionalResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsGeracaoComercialParaExportacaoInternacional> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsGeracaoComercialParaExportacaoInternacionalService)
      .find(id)
      .pipe(
        mergeMap((onsGeracaoComercialParaExportacaoInternacional: HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>) => {
          if (onsGeracaoComercialParaExportacaoInternacional.body) {
            return of(onsGeracaoComercialParaExportacaoInternacional.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsGeracaoComercialParaExportacaoInternacionalResolve;
