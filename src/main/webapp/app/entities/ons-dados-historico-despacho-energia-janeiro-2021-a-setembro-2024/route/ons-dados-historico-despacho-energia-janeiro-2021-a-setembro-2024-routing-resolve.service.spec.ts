import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';

import onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-routing-resolve.service';

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service;
  let resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024:
    | IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
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
    service = TestBed.inject(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service);
    resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest
        .spyOn(service, 'find')
        .mockReturnValue(of(new HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
