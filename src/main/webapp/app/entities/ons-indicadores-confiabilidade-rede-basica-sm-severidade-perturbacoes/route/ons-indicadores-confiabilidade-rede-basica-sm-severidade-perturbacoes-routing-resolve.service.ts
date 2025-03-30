import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';

const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>,
          ) => {
            if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.body) {
              return of(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResolve;
