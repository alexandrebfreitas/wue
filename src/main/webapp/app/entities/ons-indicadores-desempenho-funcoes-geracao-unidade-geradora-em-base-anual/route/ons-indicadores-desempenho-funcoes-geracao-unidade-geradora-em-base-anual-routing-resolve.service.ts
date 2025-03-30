import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';

const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>,
          ) => {
            if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.body) {
              return of(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve;
