import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';
import { OnsEnaDiarioBaciaService } from '../service/ons-ena-diario-bacia.service';

import onsEnaDiarioBaciaResolve from './ons-ena-diario-bacia-routing-resolve.service';

describe('OnsEnaDiarioBacia routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEnaDiarioBaciaService;
  let resultOnsEnaDiarioBacia: IOnsEnaDiarioBacia | null | undefined;

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
    service = TestBed.inject(OnsEnaDiarioBaciaService);
    resultOnsEnaDiarioBacia = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEnaDiarioBacia returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioBaciaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioBacia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioBacia).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioBaciaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioBacia = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEnaDiarioBacia).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEnaDiarioBacia>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioBaciaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioBacia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioBacia).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
