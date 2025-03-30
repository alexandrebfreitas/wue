import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';

import onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-routing-resolve.service';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService;
  let resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas:
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
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
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService);
    resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
