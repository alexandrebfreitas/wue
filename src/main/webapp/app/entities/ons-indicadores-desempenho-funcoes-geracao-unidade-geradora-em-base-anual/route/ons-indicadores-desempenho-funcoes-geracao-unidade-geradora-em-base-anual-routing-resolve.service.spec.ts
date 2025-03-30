import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';

import onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-routing-resolve.service';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService;
  let resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual:
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
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
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService);
    resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
