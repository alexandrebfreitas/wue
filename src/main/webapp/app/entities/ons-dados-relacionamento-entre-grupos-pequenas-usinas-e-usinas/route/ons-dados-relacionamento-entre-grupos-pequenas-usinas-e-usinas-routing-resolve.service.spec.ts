import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';
import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService } from '../service/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.service';

import onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-routing-resolve.service';

describe('OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService;
  let resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas:
    | IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
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
    service = TestBed.inject(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService);
    resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
