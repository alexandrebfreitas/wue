import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.test-samples';

import {
  OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService,
  RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
} from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';

const requireRestSample: RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = {
  ...sampleWithRequiredData,
  dinReferencia: sampleWithRequiredData.dinReferencia?.format(DATE_FORMAT),
};

describe('OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa Service', () => {
  let service: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
    | IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      const queryObject: any = {
        page: 0,
        size: 20,
        query: '',
        sort: [],
      };
      service.search(queryObject).subscribe(() => expectedResult);

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(null, { status: 500, statusText: 'Internal Server Error' });
      expect(expectedResult).toBe(null);
    });

    describe('addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing', () => {
      it('should add a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa to an empty array', () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          [],
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
      });

      it('should not add a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa to an array that contains it', () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
          sampleWithRequiredData;
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[] = [
          {
            ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection,
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa to an array that doesn't contain it", () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
          sampleWithRequiredData;
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection,
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
      });

      it('should add only unique OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa to an array', () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaArray: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection,
          ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
          sampleWithRequiredData;
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa2: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          [],
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
        expect(expectedResult).toContain(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          [],
          null,
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
      });

      it('should return initial array if no OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa is added', () => {
        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing(
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection);
      });
    });

    describe('compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 30063 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity1, entity2);
        const compareResult2 = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 30063 };
        const entity2 = { id: 7593 };

        const compareResult1 = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity1, entity2);
        const compareResult2 = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 30063 };
        const entity2 = { id: 30063 };

        const compareResult1 = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity1, entity2);
        const compareResult2 = service.compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
