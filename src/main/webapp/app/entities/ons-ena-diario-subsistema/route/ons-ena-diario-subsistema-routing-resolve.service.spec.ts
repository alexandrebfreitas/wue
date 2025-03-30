import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import { OnsEnaDiarioSubsistemaService } from '../service/ons-ena-diario-subsistema.service';

import onsEnaDiarioSubsistemaResolve from './ons-ena-diario-subsistema-routing-resolve.service';

describe('OnsEnaDiarioSubsistema routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEnaDiarioSubsistemaService;
  let resultOnsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema | null | undefined;

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
    service = TestBed.inject(OnsEnaDiarioSubsistemaService);
    resultOnsEnaDiarioSubsistema = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEnaDiarioSubsistema returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioSubsistemaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioSubsistema = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioSubsistema).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioSubsistemaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioSubsistema = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEnaDiarioSubsistema).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEnaDiarioSubsistema>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioSubsistemaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioSubsistema = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioSubsistema).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
