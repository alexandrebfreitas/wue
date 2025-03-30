import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';
import { OnsLinhasTransmissaoRedeOperacaoService } from '../service/ons-linhas-transmissao-rede-operacao.service';

const onsLinhasTransmissaoRedeOperacaoResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsLinhasTransmissaoRedeOperacao> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsLinhasTransmissaoRedeOperacaoService)
      .find(id)
      .pipe(
        mergeMap((onsLinhasTransmissaoRedeOperacao: HttpResponse<IOnsLinhasTransmissaoRedeOperacao>) => {
          if (onsLinhasTransmissaoRedeOperacao.body) {
            return of(onsLinhasTransmissaoRedeOperacao.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsLinhasTransmissaoRedeOperacaoResolve;
