import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import { OnsDadosValoresProgramacaoDiariaService } from '../service/ons-dados-valores-programacao-diaria.service';

import onsDadosValoresProgramacaoDiariaResolve from './ons-dados-valores-programacao-diaria-routing-resolve.service';

describe('OnsDadosValoresProgramacaoDiaria routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsDadosValoresProgramacaoDiariaService;
  let resultOnsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria | null | undefined;

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
    service = TestBed.inject(OnsDadosValoresProgramacaoDiariaService);
    resultOnsDadosValoresProgramacaoDiaria = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsDadosValoresProgramacaoDiaria returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosValoresProgramacaoDiariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosValoresProgramacaoDiaria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosValoresProgramacaoDiaria).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosValoresProgramacaoDiariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosValoresProgramacaoDiaria = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsDadosValoresProgramacaoDiaria).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsDadosValoresProgramacaoDiaria>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsDadosValoresProgramacaoDiariaResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsDadosValoresProgramacaoDiaria = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsDadosValoresProgramacaoDiaria).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
