import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.test-samples';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';

const requireRestSample: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = {
  ...sampleWithRequiredData,
};

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual Service', () => {
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService);
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

    it('should create a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
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

    describe('addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing', () => {
      it('should add a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual to an empty array', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          [],
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual);
      });

      it('should not add a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual to an array that contains it', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          sampleWithRequiredData;
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[] =
          [
            {
              ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual to an array that doesn't contain it", () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          sampleWithRequiredData;
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual);
      });

      it('should add only unique OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual to an array', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualArray: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection,
          ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          sampleWithRequiredData;
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual2: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          sampleWithPartialData;
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          [],
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual2);
      });

      it('should accept null and undefined values', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          [],
          null,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual);
      });

      it('should return initial array if no OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual is added', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection);
      });
    });

    describe('compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 28296 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 28296 };
        const entity2 = { id: 10370 };

        const compareResult1 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 28296 };
        const entity2 = { id: 28296 };

        const compareResult1 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
