import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';
import { OnsImportacaoEnergiaNaModalidadeComercialBlocoService } from '../service/ons-importacao-energia-na-modalidade-comercial-bloco.service';

import onsImportacaoEnergiaNaModalidadeComercialBlocoResolve from './ons-importacao-energia-na-modalidade-comercial-bloco-routing-resolve.service';

describe('OnsImportacaoEnergiaNaModalidadeComercialBloco routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsImportacaoEnergiaNaModalidadeComercialBlocoService;
  let resultOnsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco | null | undefined;

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
    service = TestBed.inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoService);
    resultOnsImportacaoEnergiaNaModalidadeComercialBloco = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsImportacaoEnergiaNaModalidadeComercialBloco returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsImportacaoEnergiaNaModalidadeComercialBlocoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsImportacaoEnergiaNaModalidadeComercialBloco = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsImportacaoEnergiaNaModalidadeComercialBloco).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsImportacaoEnergiaNaModalidadeComercialBlocoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsImportacaoEnergiaNaModalidadeComercialBloco = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsImportacaoEnergiaNaModalidadeComercialBloco).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsImportacaoEnergiaNaModalidadeComercialBlocoResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsImportacaoEnergiaNaModalidadeComercialBloco = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsImportacaoEnergiaNaModalidadeComercialBloco).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
