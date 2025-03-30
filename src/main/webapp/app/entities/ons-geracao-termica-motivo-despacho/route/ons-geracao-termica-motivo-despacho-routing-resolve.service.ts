import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';
import { OnsGeracaoTermicaMotivoDespachoService } from '../service/ons-geracao-termica-motivo-despacho.service';

const onsGeracaoTermicaMotivoDespachoResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsGeracaoTermicaMotivoDespacho> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsGeracaoTermicaMotivoDespachoService)
      .find(id)
      .pipe(
        mergeMap((onsGeracaoTermicaMotivoDespacho: HttpResponse<IOnsGeracaoTermicaMotivoDespacho>) => {
          if (onsGeracaoTermicaMotivoDespacho.body) {
            return of(onsGeracaoTermicaMotivoDespacho.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsGeracaoTermicaMotivoDespachoResolve;
