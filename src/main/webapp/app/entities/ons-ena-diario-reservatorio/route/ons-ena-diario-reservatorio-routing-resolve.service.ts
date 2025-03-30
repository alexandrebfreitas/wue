import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import { OnsEnaDiarioReservatorioService } from '../service/ons-ena-diario-reservatorio.service';

const onsEnaDiarioReservatorioResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEnaDiarioReservatorio> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEnaDiarioReservatorioService)
      .find(id)
      .pipe(
        mergeMap((onsEnaDiarioReservatorio: HttpResponse<IOnsEnaDiarioReservatorio>) => {
          if (onsEnaDiarioReservatorio.body) {
            return of(onsEnaDiarioReservatorio.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEnaDiarioReservatorioResolve;
