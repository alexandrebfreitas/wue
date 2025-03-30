import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';
import { OnsEarDiarioReservatorioService } from '../service/ons-ear-diario-reservatorio.service';

const onsEarDiarioReservatorioResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEarDiarioReservatorio> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEarDiarioReservatorioService)
      .find(id)
      .pipe(
        mergeMap((onsEarDiarioReservatorio: HttpResponse<IOnsEarDiarioReservatorio>) => {
          if (onsEarDiarioReservatorio.body) {
            return of(onsEarDiarioReservatorio.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEarDiarioReservatorioResolve;
