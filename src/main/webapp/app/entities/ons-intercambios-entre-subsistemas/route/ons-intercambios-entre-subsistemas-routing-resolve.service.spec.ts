import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';
import { OnsIntercambiosEntreSubsistemasService } from '../service/ons-intercambios-entre-subsistemas.service';

import onsIntercambiosEntreSubsistemasResolve from './ons-intercambios-entre-subsistemas-routing-resolve.service';

describe('OnsIntercambiosEntreSubsistemas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsIntercambiosEntreSubsistemasService;
  let resultOnsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas | null | undefined;

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
    service = TestBed.inject(OnsIntercambiosEntreSubsistemasService);
    resultOnsIntercambiosEntreSubsistemas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsIntercambiosEntreSubsistemas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIntercambiosEntreSubsistemasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIntercambiosEntreSubsistemas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIntercambiosEntreSubsistemas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIntercambiosEntreSubsistemasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIntercambiosEntreSubsistemas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsIntercambiosEntreSubsistemas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsIntercambiosEntreSubsistemas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIntercambiosEntreSubsistemasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIntercambiosEntreSubsistemas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIntercambiosEntreSubsistemas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
