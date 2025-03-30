import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';
import { OnsEarDiarioSubsistemaService } from '../service/ons-ear-diario-subsistema.service';

import onsEarDiarioSubsistemaResolve from './ons-ear-diario-subsistema-routing-resolve.service';

describe('OnsEarDiarioSubsistema routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEarDiarioSubsistemaService;
  let resultOnsEarDiarioSubsistema: IOnsEarDiarioSubsistema | null | undefined;

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
    service = TestBed.inject(OnsEarDiarioSubsistemaService);
    resultOnsEarDiarioSubsistema = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEarDiarioSubsistema returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioSubsistemaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioSubsistema = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioSubsistema).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioSubsistemaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioSubsistema = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEarDiarioSubsistema).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEarDiarioSubsistema>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioSubsistemaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioSubsistema = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioSubsistema).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
