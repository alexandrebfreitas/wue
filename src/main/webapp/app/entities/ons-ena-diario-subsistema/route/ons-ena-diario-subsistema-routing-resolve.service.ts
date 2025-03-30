import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import { OnsEnaDiarioSubsistemaService } from '../service/ons-ena-diario-subsistema.service';

const onsEnaDiarioSubsistemaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEnaDiarioSubsistema> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEnaDiarioSubsistemaService)
      .find(id)
      .pipe(
        mergeMap((onsEnaDiarioSubsistema: HttpResponse<IOnsEnaDiarioSubsistema>) => {
          if (onsEnaDiarioSubsistema.body) {
            return of(onsEnaDiarioSubsistema.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEnaDiarioSubsistemaResolve;
