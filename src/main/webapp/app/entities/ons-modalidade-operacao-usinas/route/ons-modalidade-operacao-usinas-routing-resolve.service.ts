import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';
import { OnsModalidadeOperacaoUsinasService } from '../service/ons-modalidade-operacao-usinas.service';

const onsModalidadeOperacaoUsinasResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsModalidadeOperacaoUsinas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsModalidadeOperacaoUsinasService)
      .find(id)
      .pipe(
        mergeMap((onsModalidadeOperacaoUsinas: HttpResponse<IOnsModalidadeOperacaoUsinas>) => {
          if (onsModalidadeOperacaoUsinas.body) {
            return of(onsModalidadeOperacaoUsinas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsModalidadeOperacaoUsinasResolve;
