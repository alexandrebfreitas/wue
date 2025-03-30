import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';
import { OnsEarDiarioSubsistemaService } from '../service/ons-ear-diario-subsistema.service';

const onsEarDiarioSubsistemaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEarDiarioSubsistema> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEarDiarioSubsistemaService)
      .find(id)
      .pipe(
        mergeMap((onsEarDiarioSubsistema: HttpResponse<IOnsEarDiarioSubsistema>) => {
          if (onsEarDiarioSubsistema.body) {
            return of(onsEarDiarioSubsistema.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEarDiarioSubsistemaResolve;
