import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosHidraulicosReservatorioBaseDiaria } from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';
import { OnsDadosHidraulicosReservatorioBaseDiariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-diaria.service';

const onsDadosHidraulicosReservatorioBaseDiariaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosHidraulicosReservatorioBaseDiaria> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosHidraulicosReservatorioBaseDiariaService)
      .find(id)
      .pipe(
        mergeMap((onsDadosHidraulicosReservatorioBaseDiaria: HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria>) => {
          if (onsDadosHidraulicosReservatorioBaseDiaria.body) {
            return of(onsDadosHidraulicosReservatorioBaseDiaria.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosHidraulicosReservatorioBaseDiariaResolve;
