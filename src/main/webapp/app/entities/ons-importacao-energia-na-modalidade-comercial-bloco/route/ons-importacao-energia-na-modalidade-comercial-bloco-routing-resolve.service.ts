import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';
import { OnsImportacaoEnergiaNaModalidadeComercialBlocoService } from '../service/ons-importacao-energia-na-modalidade-comercial-bloco.service';

const onsImportacaoEnergiaNaModalidadeComercialBlocoResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsImportacaoEnergiaNaModalidadeComercialBloco> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoService)
      .find(id)
      .pipe(
        mergeMap((onsImportacaoEnergiaNaModalidadeComercialBloco: HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>) => {
          if (onsImportacaoEnergiaNaModalidadeComercialBloco.body) {
            return of(onsImportacaoEnergiaNaModalidadeComercialBloco.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsImportacaoEnergiaNaModalidadeComercialBlocoResolve;
