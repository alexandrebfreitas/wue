import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService)
      .find(id)
      .pipe(
        mergeMap(
          (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>) => {
            if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.body) {
              return of(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResolve;
