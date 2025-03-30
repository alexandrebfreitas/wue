import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';
import { OnsDadosRelacionamentoEntreConjuntosEUsinasService } from '../service/ons-dados-relacionamento-entre-conjuntos-e-usinas.service';

import onsDadosRelacionamentoEntreConjuntosEUsinasResolve from './ons-dados-relacionamento-entre-conjuntos-e-usinas-routing-resolve.service';

describe('OnsDadosRelacionamentoEntreConjuntosEUsinas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosRelacionamentoEntreConjuntosEUsinasService;
  let resultOnsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas | null | undefined;

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
    service = TestBed.inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService);
    resultOnsDadosRelacionamentoEntreConjuntosEUsinas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosRelacionamentoEntreConjuntosEUsinas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRelacionamentoEntreConjuntosEUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRelacionamentoEntreConjuntosEUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRelacionamentoEntreConjuntosEUsinas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRelacionamentoEntreConjuntosEUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRelacionamentoEntreConjuntosEUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosRelacionamentoEntreConjuntosEUsinas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRelacionamentoEntreConjuntosEUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRelacionamentoEntreConjuntosEUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRelacionamentoEntreConjuntosEUsinas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
