import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';
import { OnsIntercambioSinComOutrosPaisesService } from '../service/ons-intercambio-sin-com-outros-paises.service';

import onsIntercambioSinComOutrosPaisesResolve from './ons-intercambio-sin-com-outros-paises-routing-resolve.service';

describe('OnsIntercambioSinComOutrosPaises routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsIntercambioSinComOutrosPaisesService;
  let resultOnsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises | null | undefined;

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
    service = TestBed.inject(OnsIntercambioSinComOutrosPaisesService);
    resultOnsIntercambioSinComOutrosPaises = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsIntercambioSinComOutrosPaises returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIntercambioSinComOutrosPaisesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIntercambioSinComOutrosPaises = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIntercambioSinComOutrosPaises).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIntercambioSinComOutrosPaisesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIntercambioSinComOutrosPaises = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsIntercambioSinComOutrosPaises).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsIntercambioSinComOutrosPaises>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIntercambioSinComOutrosPaisesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIntercambioSinComOutrosPaises = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIntercambioSinComOutrosPaises).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
