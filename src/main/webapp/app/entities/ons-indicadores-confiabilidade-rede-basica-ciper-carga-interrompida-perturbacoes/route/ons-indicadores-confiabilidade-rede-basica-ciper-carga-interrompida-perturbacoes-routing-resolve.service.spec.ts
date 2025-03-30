import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';

import onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-routing-resolve.service';

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService;
  let resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes:
    | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
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
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService);
    resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
