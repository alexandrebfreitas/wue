import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';
import { OnsIntercambiosEntreSubsistemasService } from '../service/ons-intercambios-entre-subsistemas.service';

const onsIntercambiosEntreSubsistemasResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsIntercambiosEntreSubsistemas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIntercambiosEntreSubsistemasService)
      .find(id)
      .pipe(
        mergeMap((onsIntercambiosEntreSubsistemas: HttpResponse<IOnsIntercambiosEntreSubsistemas>) => {
          if (onsIntercambiosEntreSubsistemas.body) {
            return of(onsIntercambiosEntreSubsistemas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsIntercambiosEntreSubsistemasResolve;
