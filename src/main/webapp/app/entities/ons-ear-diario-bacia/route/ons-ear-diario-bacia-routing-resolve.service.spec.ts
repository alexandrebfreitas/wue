import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';
import { OnsEarDiarioBaciaService } from '../service/ons-ear-diario-bacia.service';

import onsEarDiarioBaciaResolve from './ons-ear-diario-bacia-routing-resolve.service';

describe('OnsEarDiarioBacia routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEarDiarioBaciaService;
  let resultOnsEarDiarioBacia: IOnsEarDiarioBacia | null | undefined;

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
    service = TestBed.inject(OnsEarDiarioBaciaService);
    resultOnsEarDiarioBacia = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEarDiarioBacia returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioBaciaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioBacia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioBacia).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioBaciaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioBacia = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEarDiarioBacia).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEarDiarioBacia>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioBaciaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioBacia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioBacia).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
