import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';
import { OnsDadosHidraulicosReservatorioBaseHorariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-horaria.service';

import onsDadosHidraulicosReservatorioBaseHorariaResolve from './ons-dados-hidraulicos-reservatorio-base-horaria-routing-resolve.service';

describe('OnsDadosHidraulicosReservatorioBaseHoraria routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosHidraulicosReservatorioBaseHorariaService;
  let resultOnsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria | null | undefined;

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
    service = TestBed.inject(OnsDadosHidraulicosReservatorioBaseHorariaService);
    resultOnsDadosHidraulicosReservatorioBaseHoraria = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosHidraulicosReservatorioBaseHoraria returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHidraulicosReservatorioBaseHorariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHidraulicosReservatorioBaseHoraria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosHidraulicosReservatorioBaseHoraria).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHidraulicosReservatorioBaseHorariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHidraulicosReservatorioBaseHoraria = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosHidraulicosReservatorioBaseHoraria).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHidraulicosReservatorioBaseHorariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHidraulicosReservatorioBaseHoraria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosHidraulicosReservatorioBaseHoraria).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
