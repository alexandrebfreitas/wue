import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.test-samples';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';

const requireRestSample: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = {
  ...sampleWithRequiredData,
};

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal Service', () => {
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
    | IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService);
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

    it('should create a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
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

    describe('addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing', () => {
      it('should add a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal to an empty array', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          [],
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal);
      });

      it('should not add a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal to an array that contains it', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          sampleWithRequiredData;
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[] =
          [
            {
              ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal to an array that doesn't contain it", () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          sampleWithRequiredData;
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal);
      });

      it('should add only unique OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal to an array', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalArray: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection,
          ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          sampleWithRequiredData;
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal2: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          sampleWithPartialData;
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          [],
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal2);
      });

      it('should accept null and undefined values', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          [],
          null,
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal);
      });

      it('should return initial array if no OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal is added', () => {
        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection);
      });
    });

    describe('compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 18913 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 18913 };
        const entity2 = { id: 10386 };

        const compareResult1 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 18913 };
        const entity2 = { id: 18913 };

        const compareResult1 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
