import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosProgramadosElementosFluxoControlado } from '../ons-dados-programados-elementos-fluxo-controlado.model';
import { OnsDadosProgramadosElementosFluxoControladoService } from '../service/ons-dados-programados-elementos-fluxo-controlado.service';

const onsDadosProgramadosElementosFluxoControladoResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosProgramadosElementosFluxoControlado> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosProgramadosElementosFluxoControladoService)
      .find(id)
      .pipe(
        mergeMap((onsDadosProgramadosElementosFluxoControlado: HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>) => {
          if (onsDadosProgramadosElementosFluxoControlado.body) {
            return of(onsDadosProgramadosElementosFluxoControlado.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosProgramadosElementosFluxoControladoResolve;
