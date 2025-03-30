import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ena-diario-subsistema.test-samples';

import { OnsEnaDiarioSubsistemaService } from './ons-ena-diario-subsistema.service';

const requireRestSample: IOnsEnaDiarioSubsistema = {
  ...sampleWithRequiredData,
};

describe('OnsEnaDiarioSubsistema Service', () => {
  let service: OnsEnaDiarioSubsistemaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEnaDiarioSubsistema | IOnsEnaDiarioSubsistema[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEnaDiarioSubsistemaService);
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

    it('should create a OnsEnaDiarioSubsistema', () => {
      const onsEnaDiarioSubsistema = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEnaDiarioSubsistema).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEnaDiarioSubsistema', () => {
      const onsEnaDiarioSubsistema = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEnaDiarioSubsistema).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEnaDiarioSubsistema', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEnaDiarioSubsistema', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEnaDiarioSubsistema', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEnaDiarioSubsistema', () => {
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

    describe('addOnsEnaDiarioSubsistemaToCollectionIfMissing', () => {
      it('should add a OnsEnaDiarioSubsistema to an empty array', () => {
        const onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing([], onsEnaDiarioSubsistema);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioSubsistema);
      });

      it('should not add a OnsEnaDiarioSubsistema to an array that contains it', () => {
        const onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema = sampleWithRequiredData;
        const onsEnaDiarioSubsistemaCollection: IOnsEnaDiarioSubsistema[] = [
          {
            ...onsEnaDiarioSubsistema,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing(onsEnaDiarioSubsistemaCollection, onsEnaDiarioSubsistema);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEnaDiarioSubsistema to an array that doesn't contain it", () => {
        const onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema = sampleWithRequiredData;
        const onsEnaDiarioSubsistemaCollection: IOnsEnaDiarioSubsistema[] = [sampleWithPartialData];
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing(onsEnaDiarioSubsistemaCollection, onsEnaDiarioSubsistema);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioSubsistema);
      });

      it('should add only unique OnsEnaDiarioSubsistema to an array', () => {
        const onsEnaDiarioSubsistemaArray: IOnsEnaDiarioSubsistema[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsEnaDiarioSubsistemaCollection: IOnsEnaDiarioSubsistema[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing(
          onsEnaDiarioSubsistemaCollection,
          ...onsEnaDiarioSubsistemaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema = sampleWithRequiredData;
        const onsEnaDiarioSubsistema2: IOnsEnaDiarioSubsistema = sampleWithPartialData;
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing([], onsEnaDiarioSubsistema, onsEnaDiarioSubsistema2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioSubsistema);
        expect(expectedResult).toContain(onsEnaDiarioSubsistema2);
      });

      it('should accept null and undefined values', () => {
        const onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing([], null, onsEnaDiarioSubsistema, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioSubsistema);
      });

      it('should return initial array if no OnsEnaDiarioSubsistema is added', () => {
        const onsEnaDiarioSubsistemaCollection: IOnsEnaDiarioSubsistema[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEnaDiarioSubsistemaToCollectionIfMissing(onsEnaDiarioSubsistemaCollection, undefined, null);
        expect(expectedResult).toEqual(onsEnaDiarioSubsistemaCollection);
      });
    });

    describe('compareOnsEnaDiarioSubsistema', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEnaDiarioSubsistema(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 7404 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEnaDiarioSubsistema(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioSubsistema(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 7404 };
        const entity2 = { id: 14627 };

        const compareResult1 = service.compareOnsEnaDiarioSubsistema(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioSubsistema(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 7404 };
        const entity2 = { id: 7404 };

        const compareResult1 = service.compareOnsEnaDiarioSubsistema(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioSubsistema(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
