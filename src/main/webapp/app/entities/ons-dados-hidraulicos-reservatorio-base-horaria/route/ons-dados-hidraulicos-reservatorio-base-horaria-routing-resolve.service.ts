import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';
import { OnsDadosHidraulicosReservatorioBaseHorariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-horaria.service';

const onsDadosHidraulicosReservatorioBaseHorariaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosHidraulicosReservatorioBaseHoraria> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosHidraulicosReservatorioBaseHorariaService)
      .find(id)
      .pipe(
        mergeMap((onsDadosHidraulicosReservatorioBaseHoraria: HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>) => {
          if (onsDadosHidraulicosReservatorioBaseHoraria.body) {
            return of(onsDadosHidraulicosReservatorioBaseHoraria.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosHidraulicosReservatorioBaseHorariaResolve;
