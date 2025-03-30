import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';
import { OnsCvuUsinaTermicasService } from '../service/ons-cvu-usina-termicas.service';

import onsCvuUsinaTermicasResolve from './ons-cvu-usina-termicas-routing-resolve.service';

describe('OnsCvuUsinaTermicas routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: OnsCvuUsinaTermicasService;
  let resultOnsCvuUsinaTermicas: IOnsCvuUsinaTermicas | null | undefined;

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
    service = TestBed.inject(OnsCvuUsinaTermicasService);
    resultOnsCvuUsinaTermicas = undefined;
  });

  describe('resolve', () => {
    it('should return IOnsCvuUsinaTermicas returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCvuUsinaTermicasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCvuUsinaTermicas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCvuUsinaTermicas).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCvuUsinaTermicasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCvuUsinaTermicas = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultOnsCvuUsinaTermicas).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IOnsCvuUsinaTermicas>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        onsCvuUsinaTermicasResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultOnsCvuUsinaTermicas = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultOnsCvuUsinaTermicas).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
