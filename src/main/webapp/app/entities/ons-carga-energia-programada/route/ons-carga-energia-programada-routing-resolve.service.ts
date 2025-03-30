import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';
import { OnsCargaEnergiaProgramadaService } from '../service/ons-carga-energia-programada.service';

const onsCargaEnergiaProgramadaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCargaEnergiaProgramada> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCargaEnergiaProgramadaService)
      .find(id)
      .pipe(
        mergeMap((onsCargaEnergiaProgramada: HttpResponse<IOnsCargaEnergiaProgramada>) => {
          if (onsCargaEnergiaProgramada.body) {
            return of(onsCargaEnergiaProgramada.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCargaEnergiaProgramadaResolve;
