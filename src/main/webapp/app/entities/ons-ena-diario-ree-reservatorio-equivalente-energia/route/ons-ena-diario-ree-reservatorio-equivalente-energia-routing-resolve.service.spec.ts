import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ena-diario-ree-reservatorio-equivalente-energia.service';

import onsEnaDiarioReeReservatorioEquivalenteEnergiaResolve from './ons-ena-diario-ree-reservatorio-equivalente-energia-routing-resolve.service';

describe('OnsEnaDiarioReeReservatorioEquivalenteEnergia routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEnaDiarioReeReservatorioEquivalenteEnergiaService;
  let resultOnsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia | null | undefined;

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
    service = TestBed.inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService);
    resultOnsEnaDiarioReeReservatorioEquivalenteEnergia = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEnaDiarioReeReservatorioEquivalenteEnergia returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioReeReservatorioEquivalenteEnergiaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioReeReservatorioEquivalenteEnergia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioReeReservatorioEquivalenteEnergia).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioReeReservatorioEquivalenteEnergiaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioReeReservatorioEquivalenteEnergia = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEnaDiarioReeReservatorioEquivalenteEnergia).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEnaDiarioReeReservatorioEquivalenteEnergiaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEnaDiarioReeReservatorioEquivalenteEnergia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEnaDiarioReeReservatorioEquivalenteEnergia).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
