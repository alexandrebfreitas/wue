import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';
import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService } from '../service/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.service';

const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>,
          ) => {
            if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.body) {
              return of(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve;
