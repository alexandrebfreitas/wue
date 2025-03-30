import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-linhas-transmissao-rede-operacao.test-samples';

import {
  OnsLinhasTransmissaoRedeOperacaoService,
  RestOnsLinhasTransmissaoRedeOperacao,
} from './ons-linhas-transmissao-rede-operacao.service';

const requireRestSample: RestOnsLinhasTransmissaoRedeOperacao = {
  ...sampleWithRequiredData,
  datEntradaoperacao: sampleWithRequiredData.datEntradaoperacao?.format(DATE_FORMAT),
  datDesativacao: sampleWithRequiredData.datDesativacao?.format(DATE_FORMAT),
  datPrevista: sampleWithRequiredData.datPrevista?.format(DATE_FORMAT),
};

describe('OnsLinhasTransmissaoRedeOperacao Service', () => {
  let service: OnsLinhasTransmissaoRedeOperacaoService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsLinhasTransmissaoRedeOperacao | IOnsLinhasTransmissaoRedeOperacao[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsLinhasTransmissaoRedeOperacaoService);
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

    it('should create a OnsLinhasTransmissaoRedeOperacao', () => {
      const onsLinhasTransmissaoRedeOperacao = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsLinhasTransmissaoRedeOperacao).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsLinhasTransmissaoRedeOperacao', () => {
      const onsLinhasTransmissaoRedeOperacao = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsLinhasTransmissaoRedeOperacao).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsLinhasTransmissaoRedeOperacao', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsLinhasTransmissaoRedeOperacao', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsLinhasTransmissaoRedeOperacao', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsLinhasTransmissaoRedeOperacao', () => {
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

    describe('addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing', () => {
      it('should add a OnsLinhasTransmissaoRedeOperacao to an empty array', () => {
        const onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao = sampleWithRequiredData;
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing([], onsLinhasTransmissaoRedeOperacao);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsLinhasTransmissaoRedeOperacao);
      });

      it('should not add a OnsLinhasTransmissaoRedeOperacao to an array that contains it', () => {
        const onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao = sampleWithRequiredData;
        const onsLinhasTransmissaoRedeOperacaoCollection: IOnsLinhasTransmissaoRedeOperacao[] = [
          {
            ...onsLinhasTransmissaoRedeOperacao,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing(
          onsLinhasTransmissaoRedeOperacaoCollection,
          onsLinhasTransmissaoRedeOperacao,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsLinhasTransmissaoRedeOperacao to an array that doesn't contain it", () => {
        const onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao = sampleWithRequiredData;
        const onsLinhasTransmissaoRedeOperacaoCollection: IOnsLinhasTransmissaoRedeOperacao[] = [sampleWithPartialData];
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing(
          onsLinhasTransmissaoRedeOperacaoCollection,
          onsLinhasTransmissaoRedeOperacao,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsLinhasTransmissaoRedeOperacao);
      });

      it('should add only unique OnsLinhasTransmissaoRedeOperacao to an array', () => {
        const onsLinhasTransmissaoRedeOperacaoArray: IOnsLinhasTransmissaoRedeOperacao[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsLinhasTransmissaoRedeOperacaoCollection: IOnsLinhasTransmissaoRedeOperacao[] = [sampleWithRequiredData];
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing(
          onsLinhasTransmissaoRedeOperacaoCollection,
          ...onsLinhasTransmissaoRedeOperacaoArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao = sampleWithRequiredData;
        const onsLinhasTransmissaoRedeOperacao2: IOnsLinhasTransmissaoRedeOperacao = sampleWithPartialData;
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing(
          [],
          onsLinhasTransmissaoRedeOperacao,
          onsLinhasTransmissaoRedeOperacao2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsLinhasTransmissaoRedeOperacao);
        expect(expectedResult).toContain(onsLinhasTransmissaoRedeOperacao2);
      });

      it('should accept null and undefined values', () => {
        const onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao = sampleWithRequiredData;
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing(
          [],
          null,
          onsLinhasTransmissaoRedeOperacao,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsLinhasTransmissaoRedeOperacao);
      });

      it('should return initial array if no OnsLinhasTransmissaoRedeOperacao is added', () => {
        const onsLinhasTransmissaoRedeOperacaoCollection: IOnsLinhasTransmissaoRedeOperacao[] = [sampleWithRequiredData];
        expectedResult = service.addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing(
          onsLinhasTransmissaoRedeOperacaoCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsLinhasTransmissaoRedeOperacaoCollection);
      });
    });

    describe('compareOnsLinhasTransmissaoRedeOperacao', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsLinhasTransmissaoRedeOperacao(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 30390 };
        const entity2 = null;

        const compareResult1 = service.compareOnsLinhasTransmissaoRedeOperacao(entity1, entity2);
        const compareResult2 = service.compareOnsLinhasTransmissaoRedeOperacao(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 30390 };
        const entity2 = { id: 31406 };

        const compareResult1 = service.compareOnsLinhasTransmissaoRedeOperacao(entity1, entity2);
        const compareResult2 = service.compareOnsLinhasTransmissaoRedeOperacao(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 30390 };
        const entity2 = { id: 30390 };

        const compareResult1 = service.compareOnsLinhasTransmissaoRedeOperacao(entity1, entity2);
        const compareResult2 = service.compareOnsLinhasTransmissaoRedeOperacao(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
