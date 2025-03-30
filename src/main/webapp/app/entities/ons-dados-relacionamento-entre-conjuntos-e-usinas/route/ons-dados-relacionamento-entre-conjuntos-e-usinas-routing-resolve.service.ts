import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';
import { OnsDadosRelacionamentoEntreConjuntosEUsinasService } from '../service/ons-dados-relacionamento-entre-conjuntos-e-usinas.service';

const onsDadosRelacionamentoEntreConjuntosEUsinasResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosRelacionamentoEntreConjuntosEUsinas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService)
      .find(id)
      .pipe(
        mergeMap((onsDadosRelacionamentoEntreConjuntosEUsinas: HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>) => {
          if (onsDadosRelacionamentoEntreConjuntosEUsinas.body) {
            return of(onsDadosRelacionamentoEntreConjuntosEUsinas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosRelacionamentoEntreConjuntosEUsinasResolve;
