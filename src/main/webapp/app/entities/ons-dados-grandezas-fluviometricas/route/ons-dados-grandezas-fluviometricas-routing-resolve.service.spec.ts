import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';
import { OnsDadosGrandezasFluviometricasService } from '../service/ons-dados-grandezas-fluviometricas.service';

import onsDadosGrandezasFluviometricasResolve from './ons-dados-grandezas-fluviometricas-routing-resolve.service';

describe('OnsDadosGrandezasFluviometricas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosGrandezasFluviometricasService;
  let resultOnsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas | null | undefined;

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
    service = TestBed.inject(OnsDadosGrandezasFluviometricasService);
    resultOnsDadosGrandezasFluviometricas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosGrandezasFluviometricas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosGrandezasFluviometricasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosGrandezasFluviometricas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosGrandezasFluviometricas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosGrandezasFluviometricasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosGrandezasFluviometricas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosGrandezasFluviometricas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosGrandezasFluviometricas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosGrandezasFluviometricasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosGrandezasFluviometricas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosGrandezasFluviometricas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
