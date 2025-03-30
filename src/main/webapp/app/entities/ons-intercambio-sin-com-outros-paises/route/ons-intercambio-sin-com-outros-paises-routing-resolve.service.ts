import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';
import { OnsIntercambioSinComOutrosPaisesService } from '../service/ons-intercambio-sin-com-outros-paises.service';

const onsIntercambioSinComOutrosPaisesResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsIntercambioSinComOutrosPaises> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIntercambioSinComOutrosPaisesService)
      .find(id)
      .pipe(
        mergeMap((onsIntercambioSinComOutrosPaises: HttpResponse<IOnsIntercambioSinComOutrosPaises>) => {
          if (onsIntercambioSinComOutrosPaises.body) {
            return of(onsIntercambioSinComOutrosPaises.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsIntercambioSinComOutrosPaisesResolve;
