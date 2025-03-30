import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';

import onsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-routing-resolve.service';

describe('OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService;
  let resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq | null | undefined;

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
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);
    resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
