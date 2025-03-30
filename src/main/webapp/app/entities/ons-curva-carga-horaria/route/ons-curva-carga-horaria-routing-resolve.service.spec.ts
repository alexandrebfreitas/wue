import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';
import { OnsCurvaCargaHorariaService } from '../service/ons-curva-carga-horaria.service';

import onsCurvaCargaHorariaResolve from './ons-curva-carga-horaria-routing-resolve.service';

describe('OnsCurvaCargaHoraria routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCurvaCargaHorariaService;
  let resultOnsCurvaCargaHoraria: IOnsCurvaCargaHoraria | null | undefined;

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
    service = TestBed.inject(OnsCurvaCargaHorariaService);
    resultOnsCurvaCargaHoraria = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCurvaCargaHoraria returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCurvaCargaHorariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCurvaCargaHoraria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCurvaCargaHoraria).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCurvaCargaHorariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCurvaCargaHoraria = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCurvaCargaHoraria).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCurvaCargaHoraria>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCurvaCargaHorariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCurvaCargaHoraria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCurvaCargaHoraria).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
