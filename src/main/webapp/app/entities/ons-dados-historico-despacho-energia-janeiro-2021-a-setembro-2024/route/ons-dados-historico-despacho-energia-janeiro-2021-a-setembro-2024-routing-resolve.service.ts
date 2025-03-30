import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';

const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>,
          ) => {
            if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.body) {
              return of(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve;
