import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.service';

import onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-routing-resolve.service';

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService;
  let resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores:
    | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
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
    service = TestBed.inject(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService);
    resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(
          of(new HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>({ body: null })),
        );
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
