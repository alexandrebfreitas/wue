import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';
import { OnsBalancoEnergiaNosSubsistemasService } from '../service/ons-balanco-energia-nos-subsistemas.service';

import onsBalancoEnergiaNosSubsistemasResolve from './ons-balanco-energia-nos-subsistemas-routing-resolve.service';

describe('OnsBalancoEnergiaNosSubsistemas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsBalancoEnergiaNosSubsistemasService;
  let resultOnsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas | null | undefined;

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
    service = TestBed.inject(OnsBalancoEnergiaNosSubsistemasService);
    resultOnsBalancoEnergiaNosSubsistemas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsBalancoEnergiaNosSubsistemas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsBalancoEnergiaNosSubsistemasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsBalancoEnergiaNosSubsistemas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsBalancoEnergiaNosSubsistemas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsBalancoEnergiaNosSubsistemasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsBalancoEnergiaNosSubsistemas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsBalancoEnergiaNosSubsistemas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsBalancoEnergiaNosSubsistemas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsBalancoEnergiaNosSubsistemasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsBalancoEnergiaNosSubsistemas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsBalancoEnergiaNosSubsistemas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
