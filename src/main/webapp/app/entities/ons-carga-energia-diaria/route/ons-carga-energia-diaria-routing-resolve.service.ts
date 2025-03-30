import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';
import { OnsCargaEnergiaDiariaService } from '../service/ons-carga-energia-diaria.service';

const onsCargaEnergiaDiariaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCargaEnergiaDiaria> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCargaEnergiaDiariaService)
      .find(id)
      .pipe(
        mergeMap((onsCargaEnergiaDiaria: HttpResponse<IOnsCargaEnergiaDiaria>) => {
          if (onsCargaEnergiaDiaria.body) {
            return of(onsCargaEnergiaDiaria.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCargaEnergiaDiariaResolve;
