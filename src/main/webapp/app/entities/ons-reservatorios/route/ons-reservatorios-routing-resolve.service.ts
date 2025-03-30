import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsReservatorios } from '../ons-reservatorios.model';
import { OnsReservatoriosService } from '../service/ons-reservatorios.service';

const onsReservatoriosResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsReservatorios> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsReservatoriosService)
      .find(id)
      .pipe(
        mergeMap((onsReservatorios: HttpResponse<IOnsReservatorios>) => {
          if (onsReservatorios.body) {
            return of(onsReservatorios.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsReservatoriosResolve;
