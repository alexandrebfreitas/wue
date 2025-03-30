import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';
import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService } from '../service/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';

import onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-routing-resolve.service';

describe('OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService;
  let resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa | null | undefined;

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
    service = TestBed.inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService);
    resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
