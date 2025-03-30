import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.service';

const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>,
          ) => {
            if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.body) {
              return of(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve;
