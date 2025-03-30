import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';
import { OnsDadosHidrologicosVolumeEsperaRecomendadoService } from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';

const onsDadosHidrologicosVolumeEsperaRecomendadoResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosHidrologicosVolumeEsperaRecomendado> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService)
      .find(id)
      .pipe(
        mergeMap((onsDadosHidrologicosVolumeEsperaRecomendado: HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>) => {
          if (onsDadosHidrologicosVolumeEsperaRecomendado.body) {
            return of(onsDadosHidrologicosVolumeEsperaRecomendado.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosHidrologicosVolumeEsperaRecomendadoResolve;
