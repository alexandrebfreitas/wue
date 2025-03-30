import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-modalidade-operacao-usinas.test-samples';

import { OnsModalidadeOperacaoUsinasService } from './ons-modalidade-operacao-usinas.service';

const requireRestSample: IOnsModalidadeOperacaoUsinas = {
  ...sampleWithRequiredData,
};

describe('OnsModalidadeOperacaoUsinas Service', () => {
  let service: OnsModalidadeOperacaoUsinasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsModalidadeOperacaoUsinas | IOnsModalidadeOperacaoUsinas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsModalidadeOperacaoUsinasService);
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

    it('should create a OnsModalidadeOperacaoUsinas', () => {
      const onsModalidadeOperacaoUsinas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsModalidadeOperacaoUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsModalidadeOperacaoUsinas', () => {
      const onsModalidadeOperacaoUsinas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsModalidadeOperacaoUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsModalidadeOperacaoUsinas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsModalidadeOperacaoUsinas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsModalidadeOperacaoUsinas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsModalidadeOperacaoUsinas', () => {
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

    describe('addOnsModalidadeOperacaoUsinasToCollectionIfMissing', () => {
      it('should add a OnsModalidadeOperacaoUsinas to an empty array', () => {
        const onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas = sampleWithRequiredData;
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing([], onsModalidadeOperacaoUsinas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsModalidadeOperacaoUsinas);
      });

      it('should not add a OnsModalidadeOperacaoUsinas to an array that contains it', () => {
        const onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas = sampleWithRequiredData;
        const onsModalidadeOperacaoUsinasCollection: IOnsModalidadeOperacaoUsinas[] = [
          {
            ...onsModalidadeOperacaoUsinas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing(
          onsModalidadeOperacaoUsinasCollection,
          onsModalidadeOperacaoUsinas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsModalidadeOperacaoUsinas to an array that doesn't contain it", () => {
        const onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas = sampleWithRequiredData;
        const onsModalidadeOperacaoUsinasCollection: IOnsModalidadeOperacaoUsinas[] = [sampleWithPartialData];
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing(
          onsModalidadeOperacaoUsinasCollection,
          onsModalidadeOperacaoUsinas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsModalidadeOperacaoUsinas);
      });

      it('should add only unique OnsModalidadeOperacaoUsinas to an array', () => {
        const onsModalidadeOperacaoUsinasArray: IOnsModalidadeOperacaoUsinas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsModalidadeOperacaoUsinasCollection: IOnsModalidadeOperacaoUsinas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing(
          onsModalidadeOperacaoUsinasCollection,
          ...onsModalidadeOperacaoUsinasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas = sampleWithRequiredData;
        const onsModalidadeOperacaoUsinas2: IOnsModalidadeOperacaoUsinas = sampleWithPartialData;
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing(
          [],
          onsModalidadeOperacaoUsinas,
          onsModalidadeOperacaoUsinas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsModalidadeOperacaoUsinas);
        expect(expectedResult).toContain(onsModalidadeOperacaoUsinas2);
      });

      it('should accept null and undefined values', () => {
        const onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas = sampleWithRequiredData;
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing([], null, onsModalidadeOperacaoUsinas, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsModalidadeOperacaoUsinas);
      });

      it('should return initial array if no OnsModalidadeOperacaoUsinas is added', () => {
        const onsModalidadeOperacaoUsinasCollection: IOnsModalidadeOperacaoUsinas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsModalidadeOperacaoUsinasToCollectionIfMissing(
          onsModalidadeOperacaoUsinasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsModalidadeOperacaoUsinasCollection);
      });
    });

    describe('compareOnsModalidadeOperacaoUsinas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsModalidadeOperacaoUsinas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 6450 };
        const entity2 = null;

        const compareResult1 = service.compareOnsModalidadeOperacaoUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsModalidadeOperacaoUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 6450 };
        const entity2 = { id: 30323 };

        const compareResult1 = service.compareOnsModalidadeOperacaoUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsModalidadeOperacaoUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 6450 };
        const entity2 = { id: 6450 };

        const compareResult1 = service.compareOnsModalidadeOperacaoUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsModalidadeOperacaoUsinas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
