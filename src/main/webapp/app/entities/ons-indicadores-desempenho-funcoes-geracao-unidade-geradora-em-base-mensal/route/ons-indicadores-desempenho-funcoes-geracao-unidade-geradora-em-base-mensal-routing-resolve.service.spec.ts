import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';

import onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-routing-resolve.service';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService;
  let resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal:
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
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
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService);
    resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
