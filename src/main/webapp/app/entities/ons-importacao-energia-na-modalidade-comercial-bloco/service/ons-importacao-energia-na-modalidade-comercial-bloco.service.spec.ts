import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-importacao-energia-na-modalidade-comercial-bloco.test-samples';

import {
  OnsImportacaoEnergiaNaModalidadeComercialBlocoService,
  RestOnsImportacaoEnergiaNaModalidadeComercialBloco,
} from './ons-importacao-energia-na-modalidade-comercial-bloco.service';

const requireRestSample: RestOnsImportacaoEnergiaNaModalidadeComercialBloco = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsImportacaoEnergiaNaModalidadeComercialBloco Service', () => {
  let service: OnsImportacaoEnergiaNaModalidadeComercialBlocoService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsImportacaoEnergiaNaModalidadeComercialBloco | IOnsImportacaoEnergiaNaModalidadeComercialBloco[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoService);
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

    it('should create a OnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      const onsImportacaoEnergiaNaModalidadeComercialBloco = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsImportacaoEnergiaNaModalidadeComercialBloco).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      const onsImportacaoEnergiaNaModalidadeComercialBloco = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsImportacaoEnergiaNaModalidadeComercialBloco).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
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

    describe('addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing', () => {
      it('should add a OnsImportacaoEnergiaNaModalidadeComercialBloco to an empty array', () => {
        const onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco = sampleWithRequiredData;
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          [],
          onsImportacaoEnergiaNaModalidadeComercialBloco,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsImportacaoEnergiaNaModalidadeComercialBloco);
      });

      it('should not add a OnsImportacaoEnergiaNaModalidadeComercialBloco to an array that contains it', () => {
        const onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco = sampleWithRequiredData;
        const onsImportacaoEnergiaNaModalidadeComercialBlocoCollection: IOnsImportacaoEnergiaNaModalidadeComercialBloco[] = [
          {
            ...onsImportacaoEnergiaNaModalidadeComercialBloco,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          onsImportacaoEnergiaNaModalidadeComercialBlocoCollection,
          onsImportacaoEnergiaNaModalidadeComercialBloco,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsImportacaoEnergiaNaModalidadeComercialBloco to an array that doesn't contain it", () => {
        const onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco = sampleWithRequiredData;
        const onsImportacaoEnergiaNaModalidadeComercialBlocoCollection: IOnsImportacaoEnergiaNaModalidadeComercialBloco[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          onsImportacaoEnergiaNaModalidadeComercialBlocoCollection,
          onsImportacaoEnergiaNaModalidadeComercialBloco,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsImportacaoEnergiaNaModalidadeComercialBloco);
      });

      it('should add only unique OnsImportacaoEnergiaNaModalidadeComercialBloco to an array', () => {
        const onsImportacaoEnergiaNaModalidadeComercialBlocoArray: IOnsImportacaoEnergiaNaModalidadeComercialBloco[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsImportacaoEnergiaNaModalidadeComercialBlocoCollection: IOnsImportacaoEnergiaNaModalidadeComercialBloco[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          onsImportacaoEnergiaNaModalidadeComercialBlocoCollection,
          ...onsImportacaoEnergiaNaModalidadeComercialBlocoArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco = sampleWithRequiredData;
        const onsImportacaoEnergiaNaModalidadeComercialBloco2: IOnsImportacaoEnergiaNaModalidadeComercialBloco = sampleWithPartialData;
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          [],
          onsImportacaoEnergiaNaModalidadeComercialBloco,
          onsImportacaoEnergiaNaModalidadeComercialBloco2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsImportacaoEnergiaNaModalidadeComercialBloco);
        expect(expectedResult).toContain(onsImportacaoEnergiaNaModalidadeComercialBloco2);
      });

      it('should accept null and undefined values', () => {
        const onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco = sampleWithRequiredData;
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          [],
          null,
          onsImportacaoEnergiaNaModalidadeComercialBloco,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsImportacaoEnergiaNaModalidadeComercialBloco);
      });

      it('should return initial array if no OnsImportacaoEnergiaNaModalidadeComercialBloco is added', () => {
        const onsImportacaoEnergiaNaModalidadeComercialBlocoCollection: IOnsImportacaoEnergiaNaModalidadeComercialBloco[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing(
          onsImportacaoEnergiaNaModalidadeComercialBlocoCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsImportacaoEnergiaNaModalidadeComercialBlocoCollection);
      });
    });

    describe('compareOnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 20715 };
        const entity2 = null;

        const compareResult1 = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity1, entity2);
        const compareResult2 = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 20715 };
        const entity2 = { id: 20607 };

        const compareResult1 = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity1, entity2);
        const compareResult2 = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 20715 };
        const entity2 = { id: 20715 };

        const compareResult1 = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity1, entity2);
        const compareResult2 = service.compareOnsImportacaoEnergiaNaModalidadeComercialBloco(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
