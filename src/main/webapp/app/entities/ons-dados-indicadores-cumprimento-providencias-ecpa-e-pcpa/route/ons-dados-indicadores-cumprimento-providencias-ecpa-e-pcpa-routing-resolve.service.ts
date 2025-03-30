import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';
import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService } from '../service/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';

const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService)
      .find(id)
      .pipe(
        mergeMap(
          (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>) => {
            if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.body) {
              return of(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve;
