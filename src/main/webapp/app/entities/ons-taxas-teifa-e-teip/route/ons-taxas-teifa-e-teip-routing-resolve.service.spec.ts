import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';
import { OnsTaxasTeifaETeipService } from '../service/ons-taxas-teifa-e-teip.service';

import onsTaxasTeifaETeipResolve from './ons-taxas-teifa-e-teip-routing-resolve.service';

describe('OnsTaxasTeifaETeip routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsTaxasTeifaETeipService;
  let resultOnsTaxasTeifaETeip: IOnsTaxasTeifaETeip | null | undefined;

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
    service = TestBed.inject(OnsTaxasTeifaETeipService);
    resultOnsTaxasTeifaETeip = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsTaxasTeifaETeip returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsTaxasTeifaETeipResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsTaxasTeifaETeip = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsTaxasTeifaETeip).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsTaxasTeifaETeipResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsTaxasTeifaETeip = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsTaxasTeifaETeip).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsTaxasTeifaETeip>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsTaxasTeifaETeipResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsTaxasTeifaETeip = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsTaxasTeifaETeip).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
