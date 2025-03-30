import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ofertas-preco-para-importacao.test-samples';

import { OnsOfertasPrecoParaImportacaoService, RestOnsOfertasPrecoParaImportacao } from './ons-ofertas-preco-para-importacao.service';

const requireRestSample: RestOnsOfertasPrecoParaImportacao = {
  ...sampleWithRequiredData,
  datIniciovalidade: sampleWithRequiredData.datIniciovalidade?.format(DATE_FORMAT),
  datFimvalidade: sampleWithRequiredData.datFimvalidade?.format(DATE_FORMAT),
};

describe('OnsOfertasPrecoParaImportacao Service', () => {
  let service: OnsOfertasPrecoParaImportacaoService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsOfertasPrecoParaImportacao | IOnsOfertasPrecoParaImportacao[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsOfertasPrecoParaImportacaoService);
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

    it('should create a OnsOfertasPrecoParaImportacao', () => {
      const onsOfertasPrecoParaImportacao = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsOfertasPrecoParaImportacao).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsOfertasPrecoParaImportacao', () => {
      const onsOfertasPrecoParaImportacao = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsOfertasPrecoParaImportacao).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsOfertasPrecoParaImportacao', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsOfertasPrecoParaImportacao', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsOfertasPrecoParaImportacao', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsOfertasPrecoParaImportacao', () => {
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

    describe('addOnsOfertasPrecoParaImportacaoToCollectionIfMissing', () => {
      it('should add a OnsOfertasPrecoParaImportacao to an empty array', () => {
        const onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao = sampleWithRequiredData;
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing([], onsOfertasPrecoParaImportacao);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsOfertasPrecoParaImportacao);
      });

      it('should not add a OnsOfertasPrecoParaImportacao to an array that contains it', () => {
        const onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao = sampleWithRequiredData;
        const onsOfertasPrecoParaImportacaoCollection: IOnsOfertasPrecoParaImportacao[] = [
          {
            ...onsOfertasPrecoParaImportacao,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing(
          onsOfertasPrecoParaImportacaoCollection,
          onsOfertasPrecoParaImportacao,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsOfertasPrecoParaImportacao to an array that doesn't contain it", () => {
        const onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao = sampleWithRequiredData;
        const onsOfertasPrecoParaImportacaoCollection: IOnsOfertasPrecoParaImportacao[] = [sampleWithPartialData];
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing(
          onsOfertasPrecoParaImportacaoCollection,
          onsOfertasPrecoParaImportacao,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsOfertasPrecoParaImportacao);
      });

      it('should add only unique OnsOfertasPrecoParaImportacao to an array', () => {
        const onsOfertasPrecoParaImportacaoArray: IOnsOfertasPrecoParaImportacao[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsOfertasPrecoParaImportacaoCollection: IOnsOfertasPrecoParaImportacao[] = [sampleWithRequiredData];
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing(
          onsOfertasPrecoParaImportacaoCollection,
          ...onsOfertasPrecoParaImportacaoArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao = sampleWithRequiredData;
        const onsOfertasPrecoParaImportacao2: IOnsOfertasPrecoParaImportacao = sampleWithPartialData;
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing(
          [],
          onsOfertasPrecoParaImportacao,
          onsOfertasPrecoParaImportacao2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsOfertasPrecoParaImportacao);
        expect(expectedResult).toContain(onsOfertasPrecoParaImportacao2);
      });

      it('should accept null and undefined values', () => {
        const onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao = sampleWithRequiredData;
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing([], null, onsOfertasPrecoParaImportacao, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsOfertasPrecoParaImportacao);
      });

      it('should return initial array if no OnsOfertasPrecoParaImportacao is added', () => {
        const onsOfertasPrecoParaImportacaoCollection: IOnsOfertasPrecoParaImportacao[] = [sampleWithRequiredData];
        expectedResult = service.addOnsOfertasPrecoParaImportacaoToCollectionIfMissing(
          onsOfertasPrecoParaImportacaoCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsOfertasPrecoParaImportacaoCollection);
      });
    });

    describe('compareOnsOfertasPrecoParaImportacao', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsOfertasPrecoParaImportacao(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 1132 };
        const entity2 = null;

        const compareResult1 = service.compareOnsOfertasPrecoParaImportacao(entity1, entity2);
        const compareResult2 = service.compareOnsOfertasPrecoParaImportacao(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 1132 };
        const entity2 = { id: 24814 };

        const compareResult1 = service.compareOnsOfertasPrecoParaImportacao(entity1, entity2);
        const compareResult2 = service.compareOnsOfertasPrecoParaImportacao(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 1132 };
        const entity2 = { id: 1132 };

        const compareResult1 = service.compareOnsOfertasPrecoParaImportacao(entity1, entity2);
        const compareResult2 = service.compareOnsOfertasPrecoParaImportacao(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
