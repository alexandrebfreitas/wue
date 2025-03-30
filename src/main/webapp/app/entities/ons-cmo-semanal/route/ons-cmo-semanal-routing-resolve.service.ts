import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';
import { OnsCmoSemanalService } from '../service/ons-cmo-semanal.service';

const onsCmoSemanalResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCmoSemanal> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCmoSemanalService)
      .find(id)
      .pipe(
        mergeMap((onsCmoSemanal: HttpResponse<IOnsCmoSemanal>) => {
          if (onsCmoSemanal.body) {
            return of(onsCmoSemanal.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCmoSemanalResolve;
