import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';
import { OnsOfertasPrecoParaImportacaoService } from '../service/ons-ofertas-preco-para-importacao.service';

const onsOfertasPrecoParaImportacaoResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsOfertasPrecoParaImportacao> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsOfertasPrecoParaImportacaoService)
      .find(id)
      .pipe(
        mergeMap((onsOfertasPrecoParaImportacao: HttpResponse<IOnsOfertasPrecoParaImportacao>) => {
          if (onsOfertasPrecoParaImportacao.body) {
            return of(onsOfertasPrecoParaImportacao.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsOfertasPrecoParaImportacaoResolve;
