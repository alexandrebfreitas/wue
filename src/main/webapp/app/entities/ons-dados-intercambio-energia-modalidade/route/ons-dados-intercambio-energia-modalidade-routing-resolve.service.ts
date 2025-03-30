import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';
import { OnsDadosIntercambioEnergiaModalidadeService } from '../service/ons-dados-intercambio-energia-modalidade.service';

const onsDadosIntercambioEnergiaModalidadeResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosIntercambioEnergiaModalidade> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosIntercambioEnergiaModalidadeService)
      .find(id)
      .pipe(
        mergeMap((onsDadosIntercambioEnergiaModalidade: HttpResponse<IOnsDadosIntercambioEnergiaModalidade>) => {
          if (onsDadosIntercambioEnergiaModalidade.body) {
            return of(onsDadosIntercambioEnergiaModalidade.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosIntercambioEnergiaModalidadeResolve;
