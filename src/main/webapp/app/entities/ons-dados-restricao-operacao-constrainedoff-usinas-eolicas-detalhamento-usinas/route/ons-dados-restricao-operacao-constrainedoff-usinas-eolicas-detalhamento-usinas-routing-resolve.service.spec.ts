import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.service';

import onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-routing-resolve.service';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService;
  let resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas:
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
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
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService);
    resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
