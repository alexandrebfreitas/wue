import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-intercambios-entre-subsistemas.test-samples';

import { OnsIntercambiosEntreSubsistemasService, RestOnsIntercambiosEntreSubsistemas } from './ons-intercambios-entre-subsistemas.service';

const requireRestSample: RestOnsIntercambiosEntreSubsistemas = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsIntercambiosEntreSubsistemas Service', () => {
  let service: OnsIntercambiosEntreSubsistemasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsIntercambiosEntreSubsistemas | IOnsIntercambiosEntreSubsistemas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIntercambiosEntreSubsistemasService);
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

    it('should create a OnsIntercambiosEntreSubsistemas', () => {
      const onsIntercambiosEntreSubsistemas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsIntercambiosEntreSubsistemas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIntercambiosEntreSubsistemas', () => {
      const onsIntercambiosEntreSubsistemas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsIntercambiosEntreSubsistemas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIntercambiosEntreSubsistemas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIntercambiosEntreSubsistemas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIntercambiosEntreSubsistemas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIntercambiosEntreSubsistemas', () => {
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

    describe('addOnsIntercambiosEntreSubsistemasToCollectionIfMissing', () => {
      it('should add a OnsIntercambiosEntreSubsistemas to an empty array', () => {
        const onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas = sampleWithRequiredData;
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing([], onsIntercambiosEntreSubsistemas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIntercambiosEntreSubsistemas);
      });

      it('should not add a OnsIntercambiosEntreSubsistemas to an array that contains it', () => {
        const onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas = sampleWithRequiredData;
        const onsIntercambiosEntreSubsistemasCollection: IOnsIntercambiosEntreSubsistemas[] = [
          {
            ...onsIntercambiosEntreSubsistemas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing(
          onsIntercambiosEntreSubsistemasCollection,
          onsIntercambiosEntreSubsistemas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIntercambiosEntreSubsistemas to an array that doesn't contain it", () => {
        const onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas = sampleWithRequiredData;
        const onsIntercambiosEntreSubsistemasCollection: IOnsIntercambiosEntreSubsistemas[] = [sampleWithPartialData];
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing(
          onsIntercambiosEntreSubsistemasCollection,
          onsIntercambiosEntreSubsistemas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIntercambiosEntreSubsistemas);
      });

      it('should add only unique OnsIntercambiosEntreSubsistemas to an array', () => {
        const onsIntercambiosEntreSubsistemasArray: IOnsIntercambiosEntreSubsistemas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsIntercambiosEntreSubsistemasCollection: IOnsIntercambiosEntreSubsistemas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing(
          onsIntercambiosEntreSubsistemasCollection,
          ...onsIntercambiosEntreSubsistemasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas = sampleWithRequiredData;
        const onsIntercambiosEntreSubsistemas2: IOnsIntercambiosEntreSubsistemas = sampleWithPartialData;
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing(
          [],
          onsIntercambiosEntreSubsistemas,
          onsIntercambiosEntreSubsistemas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIntercambiosEntreSubsistemas);
        expect(expectedResult).toContain(onsIntercambiosEntreSubsistemas2);
      });

      it('should accept null and undefined values', () => {
        const onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas = sampleWithRequiredData;
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing(
          [],
          null,
          onsIntercambiosEntreSubsistemas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIntercambiosEntreSubsistemas);
      });

      it('should return initial array if no OnsIntercambiosEntreSubsistemas is added', () => {
        const onsIntercambiosEntreSubsistemasCollection: IOnsIntercambiosEntreSubsistemas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsIntercambiosEntreSubsistemasToCollectionIfMissing(
          onsIntercambiosEntreSubsistemasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIntercambiosEntreSubsistemasCollection);
      });
    });

    describe('compareOnsIntercambiosEntreSubsistemas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIntercambiosEntreSubsistemas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 25404 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIntercambiosEntreSubsistemas(entity1, entity2);
        const compareResult2 = service.compareOnsIntercambiosEntreSubsistemas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 25404 };
        const entity2 = { id: 32412 };

        const compareResult1 = service.compareOnsIntercambiosEntreSubsistemas(entity1, entity2);
        const compareResult2 = service.compareOnsIntercambiosEntreSubsistemas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 25404 };
        const entity2 = { id: 25404 };

        const compareResult1 = service.compareOnsIntercambiosEntreSubsistemas(entity1, entity2);
        const compareResult2 = service.compareOnsIntercambiosEntreSubsistemas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
