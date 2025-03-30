import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';
import { OnsCargaEnergiaMensalService } from '../service/ons-carga-energia-mensal.service';

const onsCargaEnergiaMensalResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCargaEnergiaMensal> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCargaEnergiaMensalService)
      .find(id)
      .pipe(
        mergeMap((onsCargaEnergiaMensal: HttpResponse<IOnsCargaEnergiaMensal>) => {
          if (onsCargaEnergiaMensal.body) {
            return of(onsCargaEnergiaMensal.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCargaEnergiaMensalResolve;
