import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';
import { OnsEnaDiarioBaciaService } from '../service/ons-ena-diario-bacia.service';

const onsEnaDiarioBaciaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEnaDiarioBacia> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEnaDiarioBaciaService)
      .find(id)
      .pipe(
        mergeMap((onsEnaDiarioBacia: HttpResponse<IOnsEnaDiarioBacia>) => {
          if (onsEnaDiarioBacia.body) {
            return of(onsEnaDiarioBacia.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEnaDiarioBaciaResolve;
