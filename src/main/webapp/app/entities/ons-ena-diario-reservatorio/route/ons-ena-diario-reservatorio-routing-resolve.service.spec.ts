import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import { OnsEnaDiarioReservatorioService } from '../service/ons-ena-diario-reservatorio.service';

import onsEnaDiarioReservatorioResolve from './ons-ena-diario-reservatorio-routing-resolve.service';

describe('OnsEnaDiarioReservatorio routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEnaDiarioReservatorioService;
  let resultOnsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio | null | undefined;

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
    service = TestBed.inject(OnsEnaDiarioReservatorioService);
    resultOnsEnaDiarioReservatorio = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEnaDiarioReservatorio returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioReservatorioResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioReservatorio = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioReservatorio).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioReservatorioResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioReservatorio = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEnaDiarioReservatorio).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEnaDiarioReservatorio>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioReservatorioResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioReservatorio = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioReservatorio).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
