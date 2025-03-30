import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDescontinuadoPrecipitacaoDiariaObservada } from '../ons-descontinuado-precipitacao-diaria-observada.model';
import { OnsDescontinuadoPrecipitacaoDiariaObservadaService } from '../service/ons-descontinuado-precipitacao-diaria-observada.service';

const onsDescontinuadoPrecipitacaoDiariaObservadaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDescontinuadoPrecipitacaoDiariaObservada> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDescontinuadoPrecipitacaoDiariaObservadaService)
      .find(id)
      .pipe(
        mergeMap((onsDescontinuadoPrecipitacaoDiariaObservada: HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>) => {
          if (onsDescontinuadoPrecipitacaoDiariaObservada.body) {
            return of(onsDescontinuadoPrecipitacaoDiariaObservada.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDescontinuadoPrecipitacaoDiariaObservadaResolve;
