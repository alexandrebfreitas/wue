import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';

const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService)
      .find(id)
      .pipe(
        mergeMap((onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>) => {
          if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.body) {
            return of(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve;
