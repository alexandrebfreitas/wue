import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';
import { OnsEquipamentosControleReativosService } from '../service/ons-equipamentos-controle-reativos.service';

const onsEquipamentosControleReativosResolve = (route: ActivatedRouteSnapshot): Observable<null | IOnsEquipamentosControleReativos> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsEquipamentosControleReativosService)
      .find(id)
      .pipe(
        mergeMap((onsEquipamentosControleReativos: HttpResponse<IOnsEquipamentosControleReativos>) => {
          if (onsEquipamentosControleReativos.body) {
            return of(onsEquipamentosControleReativos.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default onsEquipamentosControleReativosResolve;
