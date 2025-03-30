import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';
import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService } from '../service/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';

const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>,
          ) => {
            if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.body) {
              return of(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve;
