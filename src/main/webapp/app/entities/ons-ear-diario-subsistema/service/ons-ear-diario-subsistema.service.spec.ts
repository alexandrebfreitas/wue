import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ear-diario-subsistema.test-samples';

import { OnsEarDiarioSubsistemaService, RestOnsEarDiarioSubsistema } from './ons-ear-diario-subsistema.service';

const requireRestSample: RestOnsEarDiarioSubsistema = {
  ...sampleWithRequiredData,
  earData: sampleWithRequiredData.earData?.format(DATE_FORMAT),
};

describe('OnsEarDiarioSubsistema Service', () => {
  let service: OnsEarDiarioSubsistemaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEarDiarioSubsistema | IOnsEarDiarioSubsistema[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEarDiarioSubsistemaService);
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

    it('should create a OnsEarDiarioSubsistema', () => {
      const onsEarDiarioSubsistema = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEarDiarioSubsistema).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEarDiarioSubsistema', () => {
      const onsEarDiarioSubsistema = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEarDiarioSubsistema).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEarDiarioSubsistema', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEarDiarioSubsistema', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEarDiarioSubsistema', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEarDiarioSubsistema', () => {
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

    describe('addOnsEarDiarioSubsistemaToCollectionIfMissing', () => {
      it('should add a OnsEarDiarioSubsistema to an empty array', () => {
        const onsEarDiarioSubsistema: IOnsEarDiarioSubsistema = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing([], onsEarDiarioSubsistema);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioSubsistema);
      });

      it('should not add a OnsEarDiarioSubsistema to an array that contains it', () => {
        const onsEarDiarioSubsistema: IOnsEarDiarioSubsistema = sampleWithRequiredData;
        const onsEarDiarioSubsistemaCollection: IOnsEarDiarioSubsistema[] = [
          {
            ...onsEarDiarioSubsistema,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing(onsEarDiarioSubsistemaCollection, onsEarDiarioSubsistema);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEarDiarioSubsistema to an array that doesn't contain it", () => {
        const onsEarDiarioSubsistema: IOnsEarDiarioSubsistema = sampleWithRequiredData;
        const onsEarDiarioSubsistemaCollection: IOnsEarDiarioSubsistema[] = [sampleWithPartialData];
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing(onsEarDiarioSubsistemaCollection, onsEarDiarioSubsistema);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioSubsistema);
      });

      it('should add only unique OnsEarDiarioSubsistema to an array', () => {
        const onsEarDiarioSubsistemaArray: IOnsEarDiarioSubsistema[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsEarDiarioSubsistemaCollection: IOnsEarDiarioSubsistema[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing(
          onsEarDiarioSubsistemaCollection,
          ...onsEarDiarioSubsistemaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEarDiarioSubsistema: IOnsEarDiarioSubsistema = sampleWithRequiredData;
        const onsEarDiarioSubsistema2: IOnsEarDiarioSubsistema = sampleWithPartialData;
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing([], onsEarDiarioSubsistema, onsEarDiarioSubsistema2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioSubsistema);
        expect(expectedResult).toContain(onsEarDiarioSubsistema2);
      });

      it('should accept null and undefined values', () => {
        const onsEarDiarioSubsistema: IOnsEarDiarioSubsistema = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing([], null, onsEarDiarioSubsistema, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioSubsistema);
      });

      it('should return initial array if no OnsEarDiarioSubsistema is added', () => {
        const onsEarDiarioSubsistemaCollection: IOnsEarDiarioSubsistema[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEarDiarioSubsistemaToCollectionIfMissing(onsEarDiarioSubsistemaCollection, undefined, null);
        expect(expectedResult).toEqual(onsEarDiarioSubsistemaCollection);
      });
    });

    describe('compareOnsEarDiarioSubsistema', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEarDiarioSubsistema(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 31637 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEarDiarioSubsistema(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioSubsistema(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 31637 };
        const entity2 = { id: 18839 };

        const compareResult1 = service.compareOnsEarDiarioSubsistema(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioSubsistema(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 31637 };
        const entity2 = { id: 31637 };

        const compareResult1 = service.compareOnsEarDiarioSubsistema(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioSubsistema(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
