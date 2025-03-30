import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs } from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService } from '../service/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.service';

const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>,
          ) => {
            if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.body) {
              return of(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResolve;
