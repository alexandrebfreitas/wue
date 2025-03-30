import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';
import { OnsTaxasTeifaETeipService } from '../service/ons-taxas-teifa-e-teip.service';

const onsTaxasTeifaETeipResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsTaxasTeifaETeip> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsTaxasTeifaETeipService)
      .find(id)
      .pipe(
        mergeMap((onsTaxasTeifaETeip: HttpResponse<IOnsTaxasTeifaETeip>) => {
          if (onsTaxasTeifaETeip.body) {
            return of(onsTaxasTeifaETeip.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsTaxasTeifaETeipResolve;
