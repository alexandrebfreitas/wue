import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import { OnsDadosValoresProgramacaoDiariaService } from '../service/ons-dados-valores-programacao-diaria.service';

const onsDadosValoresProgramacaoDiariaResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsDadosValoresProgramacaoDiaria> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosValoresProgramacaoDiariaService)
      .find(id)
      .pipe(
        mergeMap((onsDadosValoresProgramacaoDiaria: HttpResponse<IOnsDadosValoresProgramacaoDiaria>) => {
          if (onsDadosValoresProgramacaoDiaria.body) {
            return of(onsDadosValoresProgramacaoDiaria.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsDadosValoresProgramacaoDiariaResolve;
