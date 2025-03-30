import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';
import { OnsDadosDisponibilidadeUsinasService } from '../service/ons-dados-disponibilidade-usinas.service';

const onsDadosDisponibilidadeUsinasResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsDadosDisponibilidadeUsinas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosDisponibilidadeUsinasService)
      .find(id)
      .pipe(
        mergeMap((onsDadosDisponibilidadeUsinas: HttpResponse<IOnsDadosDisponibilidadeUsinas>) => {
          if (onsDadosDisponibilidadeUsinas.body) {
            return of(onsDadosDisponibilidadeUsinas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosDisponibilidadeUsinasResolve;
