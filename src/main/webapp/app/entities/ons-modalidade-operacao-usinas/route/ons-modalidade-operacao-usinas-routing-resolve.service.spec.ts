import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';
import { OnsModalidadeOperacaoUsinasService } from '../service/ons-modalidade-operacao-usinas.service';

import onsModalidadeOperacaoUsinasResolve from './ons-modalidade-operacao-usinas-routing-resolve.service';

describe('OnsModalidadeOperacaoUsinas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsModalidadeOperacaoUsinasService;
  let resultOnsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas | null | undefined;

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
    service = TestBed.inject(OnsModalidadeOperacaoUsinasService);
    resultOnsModalidadeOperacaoUsinas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsModalidadeOperacaoUsinas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsModalidadeOperacaoUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsModalidadeOperacaoUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsModalidadeOperacaoUsinas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsModalidadeOperacaoUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsModalidadeOperacaoUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsModalidadeOperacaoUsinas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsModalidadeOperacaoUsinas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsModalidadeOperacaoUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsModalidadeOperacaoUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsModalidadeOperacaoUsinas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
