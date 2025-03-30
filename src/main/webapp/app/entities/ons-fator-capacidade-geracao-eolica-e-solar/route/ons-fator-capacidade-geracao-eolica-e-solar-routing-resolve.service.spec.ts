import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';
import { OnsFatorCapacidadeGeracaoEolicaESolarService } from '../service/ons-fator-capacidade-geracao-eolica-e-solar.service';

import onsFatorCapacidadeGeracaoEolicaESolarResolve from './ons-fator-capacidade-geracao-eolica-e-solar-routing-resolve.service';

describe('OnsFatorCapacidadeGeracaoEolicaESolar routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsFatorCapacidadeGeracaoEolicaESolarService;
  let resultOnsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar | null | undefined;

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
    service = TestBed.inject(OnsFatorCapacidadeGeracaoEolicaESolarService);
    resultOnsFatorCapacidadeGeracaoEolicaESolar = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsFatorCapacidadeGeracaoEolicaESolar returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsFatorCapacidadeGeracaoEolicaESolarResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsFatorCapacidadeGeracaoEolicaESolar = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsFatorCapacidadeGeracaoEolicaESolar).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsFatorCapacidadeGeracaoEolicaESolarResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsFatorCapacidadeGeracaoEolicaESolar = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsFatorCapacidadeGeracaoEolicaESolar).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsFatorCapacidadeGeracaoEolicaESolarResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsFatorCapacidadeGeracaoEolicaESolar = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsFatorCapacidadeGeracaoEolicaESolar).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
