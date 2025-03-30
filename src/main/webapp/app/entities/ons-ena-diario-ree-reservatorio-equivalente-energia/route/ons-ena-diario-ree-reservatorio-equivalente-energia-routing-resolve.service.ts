import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ena-diario-ree-reservatorio-equivalente-energia.service';

const onsEnaDiarioReeReservatorioEquivalenteEnergiaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsEnaDiarioReeReservatorioEquivalenteEnergia> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService)
      .find(id)
      .pipe(
        mergeMap((onsEnaDiarioReeReservatorioEquivalenteEnergia: HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>) => {
          if (onsEnaDiarioReeReservatorioEquivalenteEnergia.body) {
            return of(onsEnaDiarioReeReservatorioEquivalenteEnergia.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEnaDiarioReeReservatorioEquivalenteEnergiaResolve;
