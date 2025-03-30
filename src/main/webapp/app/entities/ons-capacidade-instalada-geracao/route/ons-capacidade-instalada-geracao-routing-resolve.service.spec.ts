import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';
import { OnsCapacidadeInstaladaGeracaoService } from '../service/ons-capacidade-instalada-geracao.service';

import onsCapacidadeInstaladaGeracaoResolve from './ons-capacidade-instalada-geracao-routing-resolve.service';

describe('OnsCapacidadeInstaladaGeracao routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCapacidadeInstaladaGeracaoService;
  let resultOnsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao | null | undefined;

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
    service = TestBed.inject(OnsCapacidadeInstaladaGeracaoService);
    resultOnsCapacidadeInstaladaGeracao = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCapacidadeInstaladaGeracao returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCapacidadeInstaladaGeracaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCapacidadeInstaladaGeracao = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCapacidadeInstaladaGeracao).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCapacidadeInstaladaGeracaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCapacidadeInstaladaGeracao = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCapacidadeInstaladaGeracao).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCapacidadeInstaladaGeracao>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCapacidadeInstaladaGeracaoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCapacidadeInstaladaGeracao = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCapacidadeInstaladaGeracao).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
