import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';

const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>,
          ) => {
            if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.body) {
              return of(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve;
