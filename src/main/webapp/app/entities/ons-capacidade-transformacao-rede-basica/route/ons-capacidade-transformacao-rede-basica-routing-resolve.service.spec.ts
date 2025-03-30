import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';
import { OnsCapacidadeTransformacaoRedeBasicaService } from '../service/ons-capacidade-transformacao-rede-basica.service';

import onsCapacidadeTransformacaoRedeBasicaResolve from './ons-capacidade-transformacao-rede-basica-routing-resolve.service';

describe('OnsCapacidadeTransformacaoRedeBasica routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCapacidadeTransformacaoRedeBasicaService;
  let resultOnsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica | null | undefined;

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
    service = TestBed.inject(OnsCapacidadeTransformacaoRedeBasicaService);
    resultOnsCapacidadeTransformacaoRedeBasica = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCapacidadeTransformacaoRedeBasica returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCapacidadeTransformacaoRedeBasicaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCapacidadeTransformacaoRedeBasica = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCapacidadeTransformacaoRedeBasica).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCapacidadeTransformacaoRedeBasicaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCapacidadeTransformacaoRedeBasica = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCapacidadeTransformacaoRedeBasica).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCapacidadeTransformacaoRedeBasicaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCapacidadeTransformacaoRedeBasica = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCapacidadeTransformacaoRedeBasica).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
