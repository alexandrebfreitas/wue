import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';
import { OnsCargaEnergiaVerificadaService } from '../service/ons-carga-energia-verificada.service';

const onsCargaEnergiaVerificadaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCargaEnergiaVerificada> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCargaEnergiaVerificadaService)
      .find(id)
      .pipe(
        mergeMap((onsCargaEnergiaVerificada: HttpResponse<IOnsCargaEnergiaVerificada>) => {
          if (onsCargaEnergiaVerificada.body) {
            return of(onsCargaEnergiaVerificada.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCargaEnergiaVerificadaResolve;
