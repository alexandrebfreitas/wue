import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEarDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ear-diario-ree-reservatorio-equivalente-energia.service';

import onsEarDiarioReeReservatorioEquivalenteEnergiaResolve from './ons-ear-diario-ree-reservatorio-equivalente-energia-routing-resolve.service';

describe('OnsEarDiarioReeReservatorioEquivalenteEnergia routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsEarDiarioReeReservatorioEquivalenteEnergiaService;
  let resultOnsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia | null | undefined;

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
    service = TestBed.inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaService);
    resultOnsEarDiarioReeReservatorioEquivalenteEnergia = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsEarDiarioReeReservatorioEquivalenteEnergia returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioReeReservatorioEquivalenteEnergiaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioReeReservatorioEquivalenteEnergia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioReeReservatorioEquivalenteEnergia).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioReeReservatorioEquivalenteEnergiaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioReeReservatorioEquivalenteEnergia = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsEarDiarioReeReservatorioEquivalenteEnergia).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsEarDiarioReeReservatorioEquivalenteEnergiaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsEarDiarioReeReservatorioEquivalenteEnergia = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsEarDiarioReeReservatorioEquivalenteEnergia).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
