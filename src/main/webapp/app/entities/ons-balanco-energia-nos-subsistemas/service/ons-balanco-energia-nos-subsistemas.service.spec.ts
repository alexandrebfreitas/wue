import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-balanco-energia-nos-subsistemas.test-samples';

import { OnsBalancoEnergiaNosSubsistemasService, RestOnsBalancoEnergiaNosSubsistemas } from './ons-balanco-energia-nos-subsistemas.service';

const requireRestSample: RestOnsBalancoEnergiaNosSubsistemas = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsBalancoEnergiaNosSubsistemas Service', () => {
  let service: OnsBalancoEnergiaNosSubsistemasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsBalancoEnergiaNosSubsistemas | IOnsBalancoEnergiaNosSubsistemas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsBalancoEnergiaNosSubsistemasService);
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

    it('should create a OnsBalancoEnergiaNosSubsistemas', () => {
      const onsBalancoEnergiaNosSubsistemas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsBalancoEnergiaNosSubsistemas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsBalancoEnergiaNosSubsistemas', () => {
      const onsBalancoEnergiaNosSubsistemas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsBalancoEnergiaNosSubsistemas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsBalancoEnergiaNosSubsistemas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsBalancoEnergiaNosSubsistemas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsBalancoEnergiaNosSubsistemas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsBalancoEnergiaNosSubsistemas', () => {
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

    describe('addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing', () => {
      it('should add a OnsBalancoEnergiaNosSubsistemas to an empty array', () => {
        const onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas = sampleWithRequiredData;
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing([], onsBalancoEnergiaNosSubsistemas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsBalancoEnergiaNosSubsistemas);
      });

      it('should not add a OnsBalancoEnergiaNosSubsistemas to an array that contains it', () => {
        const onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas = sampleWithRequiredData;
        const onsBalancoEnergiaNosSubsistemasCollection: IOnsBalancoEnergiaNosSubsistemas[] = [
          {
            ...onsBalancoEnergiaNosSubsistemas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing(
          onsBalancoEnergiaNosSubsistemasCollection,
          onsBalancoEnergiaNosSubsistemas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsBalancoEnergiaNosSubsistemas to an array that doesn't contain it", () => {
        const onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas = sampleWithRequiredData;
        const onsBalancoEnergiaNosSubsistemasCollection: IOnsBalancoEnergiaNosSubsistemas[] = [sampleWithPartialData];
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing(
          onsBalancoEnergiaNosSubsistemasCollection,
          onsBalancoEnergiaNosSubsistemas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsBalancoEnergiaNosSubsistemas);
      });

      it('should add only unique OnsBalancoEnergiaNosSubsistemas to an array', () => {
        const onsBalancoEnergiaNosSubsistemasArray: IOnsBalancoEnergiaNosSubsistemas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsBalancoEnergiaNosSubsistemasCollection: IOnsBalancoEnergiaNosSubsistemas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing(
          onsBalancoEnergiaNosSubsistemasCollection,
          ...onsBalancoEnergiaNosSubsistemasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas = sampleWithRequiredData;
        const onsBalancoEnergiaNosSubsistemas2: IOnsBalancoEnergiaNosSubsistemas = sampleWithPartialData;
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing(
          [],
          onsBalancoEnergiaNosSubsistemas,
          onsBalancoEnergiaNosSubsistemas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsBalancoEnergiaNosSubsistemas);
        expect(expectedResult).toContain(onsBalancoEnergiaNosSubsistemas2);
      });

      it('should accept null and undefined values', () => {
        const onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas = sampleWithRequiredData;
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing(
          [],
          null,
          onsBalancoEnergiaNosSubsistemas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsBalancoEnergiaNosSubsistemas);
      });

      it('should return initial array if no OnsBalancoEnergiaNosSubsistemas is added', () => {
        const onsBalancoEnergiaNosSubsistemasCollection: IOnsBalancoEnergiaNosSubsistemas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing(
          onsBalancoEnergiaNosSubsistemasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsBalancoEnergiaNosSubsistemasCollection);
      });
    });

    describe('compareOnsBalancoEnergiaNosSubsistemas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsBalancoEnergiaNosSubsistemas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 3586 };
        const entity2 = null;

        const compareResult1 = service.compareOnsBalancoEnergiaNosSubsistemas(entity1, entity2);
        const compareResult2 = service.compareOnsBalancoEnergiaNosSubsistemas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 3586 };
        const entity2 = { id: 19963 };

        const compareResult1 = service.compareOnsBalancoEnergiaNosSubsistemas(entity1, entity2);
        const compareResult2 = service.compareOnsBalancoEnergiaNosSubsistemas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 3586 };
        const entity2 = { id: 3586 };

        const compareResult1 = service.compareOnsBalancoEnergiaNosSubsistemas(entity1, entity2);
        const compareResult2 = service.compareOnsBalancoEnergiaNosSubsistemas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
