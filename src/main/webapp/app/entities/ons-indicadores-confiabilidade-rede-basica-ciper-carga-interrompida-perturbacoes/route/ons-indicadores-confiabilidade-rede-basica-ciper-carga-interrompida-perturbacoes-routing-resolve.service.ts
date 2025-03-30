import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';

const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>,
          ) => {
            if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.body) {
              return of(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve;
