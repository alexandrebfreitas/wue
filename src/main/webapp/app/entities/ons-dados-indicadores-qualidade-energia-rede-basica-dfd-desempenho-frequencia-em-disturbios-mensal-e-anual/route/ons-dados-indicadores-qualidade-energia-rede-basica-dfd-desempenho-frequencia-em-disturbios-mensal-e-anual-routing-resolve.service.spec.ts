import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual } from '../ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual.model';
import { OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualService } from '../service/ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual.service';

import onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualResolve from './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual-routing-resolve.service';

describe('OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualService;
  let resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual:
    | IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual
    | null
    | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualService);
    resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualResolve(
          mockActivatedRouteSnapshot,
        ).subscribe({
          next(result) {
            resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualResolve(
          mockActivatedRouteSnapshot,
        ).subscribe({
          next(result) {
            resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(
          of(
            new HttpResponse<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual>({ body: null }),
          ),
        );
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualResolve(
          mockActivatedRouteSnapshot,
        ).subscribe({
          next(result) {
            resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
