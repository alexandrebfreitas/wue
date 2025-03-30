import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';
import { OnsLinhasTransmissaoRedeOperacaoService } from '../service/ons-linhas-transmissao-rede-operacao.service';

import onsLinhasTransmissaoRedeOperacaoResolve from './ons-linhas-transmissao-rede-operacao-routing-resolve.service';

describe('OnsLinhasTransmissaoRedeOperacao routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsLinhasTransmissaoRedeOperacaoService;
  let resultOnsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao | null | undefined;

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
    service = TestBed.inject(OnsLinhasTransmissaoRedeOperacaoService);
    resultOnsLinhasTransmissaoRedeOperacao = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsLinhasTransmissaoRedeOperacao returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsLinhasTransmissaoRedeOperacaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsLinhasTransmissaoRedeOperacao = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsLinhasTransmissaoRedeOperacao).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsLinhasTransmissaoRedeOperacaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsLinhasTransmissaoRedeOperacao = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsLinhasTransmissaoRedeOperacao).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsLinhasTransmissaoRedeOperacao>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsLinhasTransmissaoRedeOperacaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsLinhasTransmissaoRedeOperacao = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsLinhasTransmissaoRedeOperacao).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
