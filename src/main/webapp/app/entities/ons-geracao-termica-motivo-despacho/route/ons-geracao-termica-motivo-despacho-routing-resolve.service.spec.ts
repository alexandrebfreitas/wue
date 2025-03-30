import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';
import { OnsGeracaoTermicaMotivoDespachoService } from '../service/ons-geracao-termica-motivo-despacho.service';

import onsGeracaoTermicaMotivoDespachoResolve from './ons-geracao-termica-motivo-despacho-routing-resolve.service';

describe('OnsGeracaoTermicaMotivoDespacho routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsGeracaoTermicaMotivoDespachoService;
  let resultOnsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho | null | undefined;

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
    service = TestBed.inject(OnsGeracaoTermicaMotivoDespachoService);
    resultOnsGeracaoTermicaMotivoDespacho = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsGeracaoTermicaMotivoDespacho returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsGeracaoTermicaMotivoDespachoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsGeracaoTermicaMotivoDespacho = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsGeracaoTermicaMotivoDespacho).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsGeracaoTermicaMotivoDespachoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsGeracaoTermicaMotivoDespacho = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsGeracaoTermicaMotivoDespacho).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsGeracaoTermicaMotivoDespacho>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsGeracaoTermicaMotivoDespachoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsGeracaoTermicaMotivoDespacho = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsGeracaoTermicaMotivoDespacho).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
