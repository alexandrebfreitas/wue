import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';
import { OnsDadosGrandezasFluviometricasService } from '../service/ons-dados-grandezas-fluviometricas.service';

const onsDadosGrandezasFluviometricasResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsDadosGrandezasFluviometricas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosGrandezasFluviometricasService)
      .find(id)
      .pipe(
        mergeMap((onsDadosGrandezasFluviometricas: HttpResponse<IOnsDadosGrandezasFluviometricas>) => {
          if (onsDadosGrandezasFluviometricas.body) {
            return of(onsDadosGrandezasFluviometricas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosGrandezasFluviometricasResolve;
