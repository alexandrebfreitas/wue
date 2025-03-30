import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';
import { OnsDadosHidrologicosVolumeEsperaRecomendadoService } from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';

import onsDadosHidrologicosVolumeEsperaRecomendadoResolve from './ons-dados-hidrologicos-volume-espera-recomendado-routing-resolve.service';

describe('OnsDadosHidrologicosVolumeEsperaRecomendado routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosHidrologicosVolumeEsperaRecomendadoService;
  let resultOnsDadosHidrologicosVolumeEsperaRecomendado: IOnsDadosHidrologicosVolumeEsperaRecomendado | null | undefined;

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
    service = TestBed.inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService);
    resultOnsDadosHidrologicosVolumeEsperaRecomendado = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosHidrologicosVolumeEsperaRecomendado returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHidrologicosVolumeEsperaRecomendadoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHidrologicosVolumeEsperaRecomendado = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosHidrologicosVolumeEsperaRecomendado).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHidrologicosVolumeEsperaRecomendadoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHidrologicosVolumeEsperaRecomendado = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosHidrologicosVolumeEsperaRecomendado).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHidrologicosVolumeEsperaRecomendadoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHidrologicosVolumeEsperaRecomendado = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosHidrologicosVolumeEsperaRecomendado).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
