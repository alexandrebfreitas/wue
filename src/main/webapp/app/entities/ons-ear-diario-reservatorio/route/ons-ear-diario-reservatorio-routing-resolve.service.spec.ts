import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';
import { OnsEarDiarioReservatorioService } from '../service/ons-ear-diario-reservatorio.service';

import onsEarDiarioReservatorioResolve from './ons-ear-diario-reservatorio-routing-resolve.service';

describe('OnsEarDiarioReservatorio routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEarDiarioReservatorioService;
  let resultOnsEarDiarioReservatorio: IOnsEarDiarioReservatorio | null | undefined;

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
    service = TestBed.inject(OnsEarDiarioReservatorioService);
    resultOnsEarDiarioReservatorio = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEarDiarioReservatorio returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioReservatorioResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioReservatorio = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioReservatorio).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioReservatorioResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioReservatorio = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEarDiarioReservatorio).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEarDiarioReservatorio>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioReservatorioResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioReservatorio = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioReservatorio).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
