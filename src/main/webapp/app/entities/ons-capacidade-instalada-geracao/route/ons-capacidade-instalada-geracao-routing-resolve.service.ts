import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';
import { OnsCapacidadeInstaladaGeracaoService } from '../service/ons-capacidade-instalada-geracao.service';

const onsCapacidadeInstaladaGeracaoResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCapacidadeInstaladaGeracao> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCapacidadeInstaladaGeracaoService)
      .find(id)
      .pipe(
        mergeMap((onsCapacidadeInstaladaGeracao: HttpResponse<IOnsCapacidadeInstaladaGeracao>) => {
          if (onsCapacidadeInstaladaGeracao.body) {
            return of(onsCapacidadeInstaladaGeracao.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCapacidadeInstaladaGeracaoResolve;
