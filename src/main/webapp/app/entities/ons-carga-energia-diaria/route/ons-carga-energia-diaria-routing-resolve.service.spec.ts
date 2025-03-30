import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';
import { OnsCargaEnergiaDiariaService } from '../service/ons-carga-energia-diaria.service';

import onsCargaEnergiaDiariaResolve from './ons-carga-energia-diaria-routing-resolve.service';

describe('OnsCargaEnergiaDiaria routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCargaEnergiaDiariaService;
  let resultOnsCargaEnergiaDiaria: IOnsCargaEnergiaDiaria | null | undefined;

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
    service = TestBed.inject(OnsCargaEnergiaDiariaService);
    resultOnsCargaEnergiaDiaria = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCargaEnergiaDiaria returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCargaEnergiaDiariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCargaEnergiaDiaria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCargaEnergiaDiaria).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCargaEnergiaDiariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCargaEnergiaDiaria = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCargaEnergiaDiaria).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCargaEnergiaDiaria>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCargaEnergiaDiariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCargaEnergiaDiaria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCargaEnergiaDiaria).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
