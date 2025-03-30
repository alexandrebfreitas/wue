import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-balanco-energia-dessem.test-samples';

import { OnsBalancoEnergiaDessemService, RestOnsBalancoEnergiaDessem } from './ons-balanco-energia-dessem.service';

const requireRestSample: RestOnsBalancoEnergiaDessem = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsBalancoEnergiaDessem Service', () => {
  let service: OnsBalancoEnergiaDessemService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsBalancoEnergiaDessem | IOnsBalancoEnergiaDessem[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsBalancoEnergiaDessemService);
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

    it('should create a OnsBalancoEnergiaDessem', () => {
      const onsBalancoEnergiaDessem = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsBalancoEnergiaDessem).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsBalancoEnergiaDessem', () => {
      const onsBalancoEnergiaDessem = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsBalancoEnergiaDessem).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsBalancoEnergiaDessem', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsBalancoEnergiaDessem', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsBalancoEnergiaDessem', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsBalancoEnergiaDessem', () => {
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

    describe('addOnsBalancoEnergiaDessemToCollectionIfMissing', () => {
      it('should add a OnsBalancoEnergiaDessem to an empty array', () => {
        const onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem = sampleWithRequiredData;
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing([], onsBalancoEnergiaDessem);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsBalancoEnergiaDessem);
      });

      it('should not add a OnsBalancoEnergiaDessem to an array that contains it', () => {
        const onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem = sampleWithRequiredData;
        const onsBalancoEnergiaDessemCollection: IOnsBalancoEnergiaDessem[] = [
          {
            ...onsBalancoEnergiaDessem,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing(
          onsBalancoEnergiaDessemCollection,
          onsBalancoEnergiaDessem,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsBalancoEnergiaDessem to an array that doesn't contain it", () => {
        const onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem = sampleWithRequiredData;
        const onsBalancoEnergiaDessemCollection: IOnsBalancoEnergiaDessem[] = [sampleWithPartialData];
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing(
          onsBalancoEnergiaDessemCollection,
          onsBalancoEnergiaDessem,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsBalancoEnergiaDessem);
      });

      it('should add only unique OnsBalancoEnergiaDessem to an array', () => {
        const onsBalancoEnergiaDessemArray: IOnsBalancoEnergiaDessem[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsBalancoEnergiaDessemCollection: IOnsBalancoEnergiaDessem[] = [sampleWithRequiredData];
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing(
          onsBalancoEnergiaDessemCollection,
          ...onsBalancoEnergiaDessemArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem = sampleWithRequiredData;
        const onsBalancoEnergiaDessem2: IOnsBalancoEnergiaDessem = sampleWithPartialData;
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing([], onsBalancoEnergiaDessem, onsBalancoEnergiaDessem2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsBalancoEnergiaDessem);
        expect(expectedResult).toContain(onsBalancoEnergiaDessem2);
      });

      it('should accept null and undefined values', () => {
        const onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem = sampleWithRequiredData;
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing([], null, onsBalancoEnergiaDessem, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsBalancoEnergiaDessem);
      });

      it('should return initial array if no OnsBalancoEnergiaDessem is added', () => {
        const onsBalancoEnergiaDessemCollection: IOnsBalancoEnergiaDessem[] = [sampleWithRequiredData];
        expectedResult = service.addOnsBalancoEnergiaDessemToCollectionIfMissing(onsBalancoEnergiaDessemCollection, undefined, null);
        expect(expectedResult).toEqual(onsBalancoEnergiaDessemCollection);
      });
    });

    describe('compareOnsBalancoEnergiaDessem', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsBalancoEnergiaDessem(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 3629 };
        const entity2 = null;

        const compareResult1 = service.compareOnsBalancoEnergiaDessem(entity1, entity2);
        const compareResult2 = service.compareOnsBalancoEnergiaDessem(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 3629 };
        const entity2 = { id: 10632 };

        const compareResult1 = service.compareOnsBalancoEnergiaDessem(entity1, entity2);
        const compareResult2 = service.compareOnsBalancoEnergiaDessem(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 3629 };
        const entity2 = { id: 3629 };

        const compareResult1 = service.compareOnsBalancoEnergiaDessem(entity1, entity2);
        const compareResult2 = service.compareOnsBalancoEnergiaDessem(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
