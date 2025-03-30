import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';
import { OnsGeracaoComercialParaExportacaoInternacionalService } from '../service/ons-geracao-comercial-para-exportacao-internacional.service';

import onsGeracaoComercialParaExportacaoInternacionalResolve from './ons-geracao-comercial-para-exportacao-internacional-routing-resolve.service';

describe('OnsGeracaoComercialParaExportacaoInternacional routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsGeracaoComercialParaExportacaoInternacionalService;
  let resultOnsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional | null | undefined;

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
    service = TestBed.inject(OnsGeracaoComercialParaExportacaoInternacionalService);
    resultOnsGeracaoComercialParaExportacaoInternacional = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsGeracaoComercialParaExportacaoInternacional returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsGeracaoComercialParaExportacaoInternacionalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsGeracaoComercialParaExportacaoInternacional = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsGeracaoComercialParaExportacaoInternacional).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsGeracaoComercialParaExportacaoInternacionalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsGeracaoComercialParaExportacaoInternacional = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsGeracaoComercialParaExportacaoInternacional).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsGeracaoComercialParaExportacaoInternacionalResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsGeracaoComercialParaExportacaoInternacional = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsGeracaoComercialParaExportacaoInternacional).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
