import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';
import { OnsFatorCapacidadeGeracaoEolicaESolarService } from '../service/ons-fator-capacidade-geracao-eolica-e-solar.service';

const onsFatorCapacidadeGeracaoEolicaESolarResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsFatorCapacidadeGeracaoEolicaESolar> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsFatorCapacidadeGeracaoEolicaESolarService)
      .find(id)
      .pipe(
        mergeMap((onsFatorCapacidadeGeracaoEolicaESolar: HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>) => {
          if (onsFatorCapacidadeGeracaoEolicaESolar.body) {
            return of(onsFatorCapacidadeGeracaoEolicaESolar.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsFatorCapacidadeGeracaoEolicaESolarResolve;
