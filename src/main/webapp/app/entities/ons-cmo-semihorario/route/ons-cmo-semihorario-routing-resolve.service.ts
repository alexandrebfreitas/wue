import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsCmoSemihorario } from '../ons-cmo-semihorario.model';
import { OnsCmoSemihorarioService } from '../service/ons-cmo-semihorario.service';

const onsCmoSemihorarioResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsCmoSemihorario> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsCmoSemihorarioService)
      .find(id)
      .pipe(
        mergeMap((onsCmoSemihorario: HttpResponse<IOnsCmoSemihorario>) => {
          if (onsCmoSemihorario.body) {
            return of(onsCmoSemihorario.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsCmoSemihorarioResolve;
