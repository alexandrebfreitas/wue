import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';
import { OnsDadosIntercambioEnergiaModalidadeService } from '../service/ons-dados-intercambio-energia-modalidade.service';

import onsDadosIntercambioEnergiaModalidadeResolve from './ons-dados-intercambio-energia-modalidade-routing-resolve.service';

describe('OnsDadosIntercambioEnergiaModalidade routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosIntercambioEnergiaModalidadeService;
  let resultOnsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade | null | undefined;

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
    service = TestBed.inject(OnsDadosIntercambioEnergiaModalidadeService);
    resultOnsDadosIntercambioEnergiaModalidade = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosIntercambioEnergiaModalidade returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIntercambioEnergiaModalidadeResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIntercambioEnergiaModalidade = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIntercambioEnergiaModalidade).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIntercambioEnergiaModalidadeResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIntercambioEnergiaModalidade = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosIntercambioEnergiaModalidade).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosIntercambioEnergiaModalidade>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosIntercambioEnergiaModalidadeResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosIntercambioEnergiaModalidade = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosIntercambioEnergiaModalidade).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
