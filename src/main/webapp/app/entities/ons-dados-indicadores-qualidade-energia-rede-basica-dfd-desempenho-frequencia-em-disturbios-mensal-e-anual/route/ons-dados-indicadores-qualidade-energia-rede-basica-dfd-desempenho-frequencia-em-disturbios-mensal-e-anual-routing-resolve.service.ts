import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual } from '../ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual.model';
import { OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualService } from '../service/ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual.service';

const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual> => {
  const id = route.params.id;
  if (id) {
    return inject(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualService)
      .find(id)
      .pipe(
        mergeMap(
          (
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual: HttpResponse<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual>,
          ) => {
            if (onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual.body) {
              return of(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual.body);
            }
            inject(Router).navigate(['404']);
            return EMPTY;
          },
        ),
      );
  }
  return of(null);
};

export default onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualResolve;
