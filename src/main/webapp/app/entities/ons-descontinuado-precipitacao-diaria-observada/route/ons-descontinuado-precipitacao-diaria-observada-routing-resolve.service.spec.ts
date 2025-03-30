import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDescontinuadoPrecipitacaoDiariaObservada } from '../ons-descontinuado-precipitacao-diaria-observada.model';
import { OnsDescontinuadoPrecipitacaoDiariaObservadaService } from '../service/ons-descontinuado-precipitacao-diaria-observada.service';

import onsDescontinuadoPrecipitacaoDiariaObservadaResolve from './ons-descontinuado-precipitacao-diaria-observada-routing-resolve.service';

describe('OnsDescontinuadoPrecipitacaoDiariaObservada routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDescontinuadoPrecipitacaoDiariaObservadaService;
  let resultOnsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada | null | undefined;

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
    service = TestBed.inject(OnsDescontinuadoPrecipitacaoDiariaObservadaService);
    resultOnsDescontinuadoPrecipitacaoDiariaObservada = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDescontinuadoPrecipitacaoDiariaObservada returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDescontinuadoPrecipitacaoDiariaObservadaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDescontinuadoPrecipitacaoDiariaObservada = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDescontinuadoPrecipitacaoDiariaObservada).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDescontinuadoPrecipitacaoDiariaObservadaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDescontinuadoPrecipitacaoDiariaObservada = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDescontinuadoPrecipitacaoDiariaObservada).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDescontinuadoPrecipitacaoDiariaObservadaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDescontinuadoPrecipitacaoDiariaObservada = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDescontinuadoPrecipitacaoDiariaObservada).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
