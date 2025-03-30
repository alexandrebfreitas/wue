import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';
import { OnsGeracaoUsinaEmBaseHorariaService } from '../service/ons-geracao-usina-em-base-horaria.service';

const onsGeracaoUsinaEmBaseHorariaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsGeracaoUsinaEmBaseHoraria> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsGeracaoUsinaEmBaseHorariaService)
      .find(id)
      .pipe(
        mergeMap((onsGeracaoUsinaEmBaseHoraria: HttpResponse<IOnsGeracaoUsinaEmBaseHoraria>) => {
          if (onsGeracaoUsinaEmBaseHoraria.body) {
            return of(onsGeracaoUsinaEmBaseHoraria.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsGeracaoUsinaEmBaseHorariaResolve;
