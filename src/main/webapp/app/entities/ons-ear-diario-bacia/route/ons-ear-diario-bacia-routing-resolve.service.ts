import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';
import { OnsEarDiarioBaciaService } from '../service/ons-ear-diario-bacia.service';

const onsEarDiarioBaciaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEarDiarioBacia> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEarDiarioBaciaService)
      .find(id)
      .pipe(
        mergeMap((onsEarDiarioBacia: HttpResponse<IOnsEarDiarioBacia>) => {
          if (onsEarDiarioBacia.body) {
            return of(onsEarDiarioBacia.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEarDiarioBaciaResolve;
