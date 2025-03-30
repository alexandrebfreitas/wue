import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';
import { OnsBalancoEnergiaDessemService } from '../service/ons-balanco-energia-dessem.service';

import onsBalancoEnergiaDessemResolve from './ons-balanco-energia-dessem-routing-resolve.service';

describe('OnsBalancoEnergiaDessem routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsBalancoEnergiaDessemService;
  let resultOnsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem | null | undefined;

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
    service = TestBed.inject(OnsBalancoEnergiaDessemService);
    resultOnsBalancoEnergiaDessem = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsBalancoEnergiaDessem returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsBalancoEnergiaDessemResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsBalancoEnergiaDessem = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsBalancoEnergiaDessem).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsBalancoEnergiaDessemResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsBalancoEnergiaDessem = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsBalancoEnergiaDessem).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsBalancoEnergiaDessem>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsBalancoEnergiaDessemResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsBalancoEnergiaDessem = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsBalancoEnergiaDessem).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
