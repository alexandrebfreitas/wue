import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';
import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService } from '../service/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';

import onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-routing-resolve.service';

describe('OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService;
  let resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares:
    | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
    | null
    | undefined;

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
    service = TestBed.inject(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService);
    resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
