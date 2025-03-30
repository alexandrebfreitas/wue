import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';
import { OnsCvuUsinaTermicasService } from '../service/ons-cvu-usina-termicas.service';

const onsCvuUsinaTermicasResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCvuUsinaTermicas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCvuUsinaTermicasService)
      .find(id)
      .pipe(
        mergeMap((onsCvuUsinaTermicas: HttpResponse<IOnsCvuUsinaTermicas>) => {
          if (onsCvuUsinaTermicas.body) {
            return of(onsCvuUsinaTermicas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCvuUsinaTermicasResolve;
