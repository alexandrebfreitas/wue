import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-capacidade-instalada-geracao.test-samples';

import { OnsCapacidadeInstaladaGeracaoService, RestOnsCapacidadeInstaladaGeracao } from './ons-capacidade-instalada-geracao.service';

const requireRestSample: RestOnsCapacidadeInstaladaGeracao = {
  ...sampleWithRequiredData,
  datEntradateste: sampleWithRequiredData.datEntradateste?.format(DATE_FORMAT),
  datEntradaoperacao: sampleWithRequiredData.datEntradaoperacao?.format(DATE_FORMAT),
  datDesativacao: sampleWithRequiredData.datDesativacao?.format(DATE_FORMAT),
};

describe('OnsCapacidadeInstaladaGeracao Service', () => {
  let service: OnsCapacidadeInstaladaGeracaoService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCapacidadeInstaladaGeracao | IOnsCapacidadeInstaladaGeracao[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCapacidadeInstaladaGeracaoService);
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

    it('should create a OnsCapacidadeInstaladaGeracao', () => {
      const onsCapacidadeInstaladaGeracao = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCapacidadeInstaladaGeracao).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCapacidadeInstaladaGeracao', () => {
      const onsCapacidadeInstaladaGeracao = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCapacidadeInstaladaGeracao).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCapacidadeInstaladaGeracao', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCapacidadeInstaladaGeracao', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCapacidadeInstaladaGeracao', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCapacidadeInstaladaGeracao', () => {
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

    describe('addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing', () => {
      it('should add a OnsCapacidadeInstaladaGeracao to an empty array', () => {
        const onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao = sampleWithRequiredData;
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing([], onsCapacidadeInstaladaGeracao);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCapacidadeInstaladaGeracao);
      });

      it('should not add a OnsCapacidadeInstaladaGeracao to an array that contains it', () => {
        const onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao = sampleWithRequiredData;
        const onsCapacidadeInstaladaGeracaoCollection: IOnsCapacidadeInstaladaGeracao[] = [
          {
            ...onsCapacidadeInstaladaGeracao,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing(
          onsCapacidadeInstaladaGeracaoCollection,
          onsCapacidadeInstaladaGeracao,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCapacidadeInstaladaGeracao to an array that doesn't contain it", () => {
        const onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao = sampleWithRequiredData;
        const onsCapacidadeInstaladaGeracaoCollection: IOnsCapacidadeInstaladaGeracao[] = [sampleWithPartialData];
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing(
          onsCapacidadeInstaladaGeracaoCollection,
          onsCapacidadeInstaladaGeracao,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCapacidadeInstaladaGeracao);
      });

      it('should add only unique OnsCapacidadeInstaladaGeracao to an array', () => {
        const onsCapacidadeInstaladaGeracaoArray: IOnsCapacidadeInstaladaGeracao[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsCapacidadeInstaladaGeracaoCollection: IOnsCapacidadeInstaladaGeracao[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing(
          onsCapacidadeInstaladaGeracaoCollection,
          ...onsCapacidadeInstaladaGeracaoArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao = sampleWithRequiredData;
        const onsCapacidadeInstaladaGeracao2: IOnsCapacidadeInstaladaGeracao = sampleWithPartialData;
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing(
          [],
          onsCapacidadeInstaladaGeracao,
          onsCapacidadeInstaladaGeracao2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCapacidadeInstaladaGeracao);
        expect(expectedResult).toContain(onsCapacidadeInstaladaGeracao2);
      });

      it('should accept null and undefined values', () => {
        const onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao = sampleWithRequiredData;
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing([], null, onsCapacidadeInstaladaGeracao, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCapacidadeInstaladaGeracao);
      });

      it('should return initial array if no OnsCapacidadeInstaladaGeracao is added', () => {
        const onsCapacidadeInstaladaGeracaoCollection: IOnsCapacidadeInstaladaGeracao[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing(
          onsCapacidadeInstaladaGeracaoCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsCapacidadeInstaladaGeracaoCollection);
      });
    });

    describe('compareOnsCapacidadeInstaladaGeracao', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCapacidadeInstaladaGeracao(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 5332 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCapacidadeInstaladaGeracao(entity1, entity2);
        const compareResult2 = service.compareOnsCapacidadeInstaladaGeracao(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 5332 };
        const entity2 = { id: 76 };

        const compareResult1 = service.compareOnsCapacidadeInstaladaGeracao(entity1, entity2);
        const compareResult2 = service.compareOnsCapacidadeInstaladaGeracao(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 5332 };
        const entity2 = { id: 5332 };

        const compareResult1 = service.compareOnsCapacidadeInstaladaGeracao(entity1, entity2);
        const compareResult2 = service.compareOnsCapacidadeInstaladaGeracao(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
