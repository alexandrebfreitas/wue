import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';
import { OnsEquipamentosControleReativosService } from '../service/ons-equipamentos-controle-reativos.service';

import onsEquipamentosControleReativosResolve from './ons-equipamentos-controle-reativos-routing-resolve.service';

describe('OnsEquipamentosControleReativos routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEquipamentosControleReativosService;
  let resultOnsEquipamentosControleReativos: IOnsEquipamentosControleReativos | null | undefined;

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
    service = TestBed.inject(OnsEquipamentosControleReativosService);
    resultOnsEquipamentosControleReativos = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEquipamentosControleReativos returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEquipamentosControleReativosResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEquipamentosControleReativos = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEquipamentosControleReativos).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEquipamentosControleReativosResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEquipamentosControleReativos = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEquipamentosControleReativos).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEquipamentosControleReativos>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEquipamentosControleReativosResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEquipamentosControleReativos = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEquipamentosControleReativos).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
