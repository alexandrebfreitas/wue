import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';
import { OnsBalancoEnergiaNosSubsistemasService } from '../service/ons-balanco-energia-nos-subsistemas.service';

const onsBalancoEnergiaNosSubsistemasResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsBalancoEnergiaNosSubsistemas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsBalancoEnergiaNosSubsistemasService)
      .find(id)
      .pipe(
        mergeMap((onsBalancoEnergiaNosSubsistemas: HttpResponse<IOnsBalancoEnergiaNosSubsistemas>) => {
          if (onsBalancoEnergiaNosSubsistemas.body) {
            return of(onsBalancoEnergiaNosSubsistemas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsBalancoEnergiaNosSubsistemasResolve;
