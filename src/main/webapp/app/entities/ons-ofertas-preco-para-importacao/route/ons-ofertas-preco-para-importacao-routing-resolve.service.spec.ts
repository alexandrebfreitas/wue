import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';
import { OnsOfertasPrecoParaImportacaoService } from '../service/ons-ofertas-preco-para-importacao.service';

import onsOfertasPrecoParaImportacaoResolve from './ons-ofertas-preco-para-importacao-routing-resolve.service';

describe('OnsOfertasPrecoParaImportacao routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsOfertasPrecoParaImportacaoService;
  let resultOnsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao | null | undefined;

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
    service = TestBed.inject(OnsOfertasPrecoParaImportacaoService);
    resultOnsOfertasPrecoParaImportacao = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsOfertasPrecoParaImportacao returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsOfertasPrecoParaImportacaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsOfertasPrecoParaImportacao = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsOfertasPrecoParaImportacao).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsOfertasPrecoParaImportacaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsOfertasPrecoParaImportacao = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsOfertasPrecoParaImportacao).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsOfertasPrecoParaImportacao>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsOfertasPrecoParaImportacaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsOfertasPrecoParaImportacao = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsOfertasPrecoParaImportacao).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
