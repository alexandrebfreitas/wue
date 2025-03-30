import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEarDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ear-diario-ree-reservatorio-equivalente-energia.service';

const onsEarDiarioReeReservatorioEquivalenteEnergiaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsEarDiarioReeReservatorioEquivalenteEnergia> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaService)
      .find(id)
      .pipe(
        mergeMap((onsEarDiarioReeReservatorioEquivalenteEnergia: HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>) => {
          if (onsEarDiarioReeReservatorioEquivalenteEnergia.body) {
            return of(onsEarDiarioReeReservatorioEquivalenteEnergia.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEarDiarioReeReservatorioEquivalenteEnergiaResolve;
