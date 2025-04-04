import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';
import { OnsCmoSemanalService } from '../service/ons-cmo-semanal.service';

import onsCmoSemanalResolve from './ons-cmo-semanal-routing-resolve.service';

describe('OnsCmoSemanal routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCmoSemanalService;
  let resultOnsCmoSemanal: IOnsCmoSemanal | null | undefined;

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
    service = TestBed.inject(OnsCmoSemanalService);
    resultOnsCmoSemanal = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCmoSemanal returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCmoSemanalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCmoSemanal = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCmoSemanal).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCmoSemanalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCmoSemanal = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCmoSemanal).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCmoSemanal>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCmoSemanalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCmoSemanal = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCmoSemanal).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
