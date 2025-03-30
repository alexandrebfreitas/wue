import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';
import { OnsDadosDisponibilidadeUsinasService } from '../service/ons-dados-disponibilidade-usinas.service';

import onsDadosDisponibilidadeUsinasResolve from './ons-dados-disponibilidade-usinas-routing-resolve.service';

describe('OnsDadosDisponibilidadeUsinas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosDisponibilidadeUsinasService;
  let resultOnsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas | null | undefined;

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
    service = TestBed.inject(OnsDadosDisponibilidadeUsinasService);
    resultOnsDadosDisponibilidadeUsinas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosDisponibilidadeUsinas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosDisponibilidadeUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosDisponibilidadeUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosDisponibilidadeUsinas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosDisponibilidadeUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosDisponibilidadeUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosDisponibilidadeUsinas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosDisponibilidadeUsinas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosDisponibilidadeUsinasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosDisponibilidadeUsinas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosDisponibilidadeUsinas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
