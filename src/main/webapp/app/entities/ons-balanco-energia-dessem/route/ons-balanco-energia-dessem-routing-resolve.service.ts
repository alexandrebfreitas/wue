import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';
import { OnsBalancoEnergiaDessemService } from '../service/ons-balanco-energia-dessem.service';

const onsBalancoEnergiaDessemResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsBalancoEnergiaDessem> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsBalancoEnergiaDessemService)
      .find(id)
      .pipe(
        mergeMap((onsBalancoEnergiaDessem: HttpResponse<IOnsBalancoEnergiaDessem>) => {
          if (onsBalancoEnergiaDessem.body) {
            return of(onsBalancoEnergiaDessem.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsBalancoEnergiaDessemResolve;
