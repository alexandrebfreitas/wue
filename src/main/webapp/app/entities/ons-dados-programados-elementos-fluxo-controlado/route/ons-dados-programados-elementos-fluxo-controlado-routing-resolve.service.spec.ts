import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosProgramadosElementosFluxoControlado } from '../ons-dados-programados-elementos-fluxo-controlado.model';
import { OnsDadosProgramadosElementosFluxoControladoService } from '../service/ons-dados-programados-elementos-fluxo-controlado.service';

import onsDadosProgramadosElementosFluxoControladoResolve from './ons-dados-programados-elementos-fluxo-controlado-routing-resolve.service';

describe('OnsDadosProgramadosElementosFluxoControlado routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosProgramadosElementosFluxoControladoService;
  let resultOnsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado | null | undefined;

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
    service = TestBed.inject(OnsDadosProgramadosElementosFluxoControladoService);
    resultOnsDadosProgramadosElementosFluxoControlado = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosProgramadosElementosFluxoControlado returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosProgramadosElementosFluxoControladoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosProgramadosElementosFluxoControlado = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosProgramadosElementosFluxoControlado).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosProgramadosElementosFluxoControladoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosProgramadosElementosFluxoControlado = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosProgramadosElementosFluxoControlado).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosProgramadosElementosFluxoControladoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosProgramadosElementosFluxoControlado = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosProgramadosElementosFluxoControlado).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
