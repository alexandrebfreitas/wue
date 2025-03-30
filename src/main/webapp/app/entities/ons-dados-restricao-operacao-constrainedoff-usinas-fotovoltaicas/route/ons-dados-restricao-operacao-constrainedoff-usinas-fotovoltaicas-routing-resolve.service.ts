import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>,
          ) => {
            if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.body) {
              return of(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve;
