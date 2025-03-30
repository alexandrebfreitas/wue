import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';
import { OnsCapacidadeTransformacaoRedeBasicaService } from '../service/ons-capacidade-transformacao-rede-basica.service';

const onsCapacidadeTransformacaoRedeBasicaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsCapacidadeTransformacaoRedeBasica> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCapacidadeTransformacaoRedeBasicaService)
      .find(id)
      .pipe(
        mergeMap((onsCapacidadeTransformacaoRedeBasica: HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>) => {
          if (onsCapacidadeTransformacaoRedeBasica.body) {
            return of(onsCapacidadeTransformacaoRedeBasica.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCapacidadeTransformacaoRedeBasicaResolve;
