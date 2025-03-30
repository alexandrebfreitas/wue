import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';
import { OnsCargaEnergiaProgramadaService } from '../service/ons-carga-energia-programada.service';

import onsCargaEnergiaProgramadaResolve from './ons-carga-energia-programada-routing-resolve.service';

describe('OnsCargaEnergiaProgramada routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCargaEnergiaProgramadaService;
  let resultOnsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada | null | undefined;

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
    service = TestBed.inject(OnsCargaEnergiaProgramadaService);
    resultOnsCargaEnergiaProgramada = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCargaEnergiaProgramada returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCargaEnergiaProgramadaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCargaEnergiaProgramada = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCargaEnergiaProgramada).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCargaEnergiaProgramadaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCargaEnergiaProgramada = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCargaEnergiaProgramada).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCargaEnergiaProgramada>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCargaEnergiaProgramadaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCargaEnergiaProgramada = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCargaEnergiaProgramada).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
