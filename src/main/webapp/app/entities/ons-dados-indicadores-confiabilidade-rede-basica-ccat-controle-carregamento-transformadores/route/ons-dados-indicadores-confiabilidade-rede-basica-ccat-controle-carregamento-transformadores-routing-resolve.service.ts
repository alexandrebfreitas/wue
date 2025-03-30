import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.service';

const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>,
          ) => {
            if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.body) {
              return of(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve;
