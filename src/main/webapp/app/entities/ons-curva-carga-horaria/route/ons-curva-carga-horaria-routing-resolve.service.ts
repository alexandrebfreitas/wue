import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';
import { OnsCurvaCargaHorariaService } from '../service/ons-curva-carga-horaria.service';

const onsCurvaCargaHorariaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCurvaCargaHoraria> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCurvaCargaHorariaService)
      .find(id)
      .pipe(
        mergeMap((onsCurvaCargaHoraria: HttpResponse<IOnsCurvaCargaHoraria>) => {
          if (onsCurvaCargaHoraria.body) {
            return of(onsCurvaCargaHoraria.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCurvaCargaHorariaResolve;
