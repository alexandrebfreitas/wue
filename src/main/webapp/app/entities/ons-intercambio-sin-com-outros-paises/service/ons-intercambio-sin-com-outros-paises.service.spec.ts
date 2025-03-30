import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-intercambio-sin-com-outros-paises.test-samples';

import {
  OnsIntercambioSinComOutrosPaisesService,
  RestOnsIntercambioSinComOutrosPaises,
} from './ons-intercambio-sin-com-outros-paises.service';

const requireRestSample: RestOnsIntercambioSinComOutrosPaises = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsIntercambioSinComOutrosPaises Service', () => {
  let service: OnsIntercambioSinComOutrosPaisesService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsIntercambioSinComOutrosPaises | IOnsIntercambioSinComOutrosPaises[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIntercambioSinComOutrosPaisesService);
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

    it('should create a OnsIntercambioSinComOutrosPaises', () => {
      const onsIntercambioSinComOutrosPaises = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsIntercambioSinComOutrosPaises).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIntercambioSinComOutrosPaises', () => {
      const onsIntercambioSinComOutrosPaises = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsIntercambioSinComOutrosPaises).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIntercambioSinComOutrosPaises', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIntercambioSinComOutrosPaises', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIntercambioSinComOutrosPaises', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIntercambioSinComOutrosPaises', () => {
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

    describe('addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing', () => {
      it('should add a OnsIntercambioSinComOutrosPaises to an empty array', () => {
        const onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises = sampleWithRequiredData;
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing([], onsIntercambioSinComOutrosPaises);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIntercambioSinComOutrosPaises);
      });

      it('should not add a OnsIntercambioSinComOutrosPaises to an array that contains it', () => {
        const onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises = sampleWithRequiredData;
        const onsIntercambioSinComOutrosPaisesCollection: IOnsIntercambioSinComOutrosPaises[] = [
          {
            ...onsIntercambioSinComOutrosPaises,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing(
          onsIntercambioSinComOutrosPaisesCollection,
          onsIntercambioSinComOutrosPaises,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIntercambioSinComOutrosPaises to an array that doesn't contain it", () => {
        const onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises = sampleWithRequiredData;
        const onsIntercambioSinComOutrosPaisesCollection: IOnsIntercambioSinComOutrosPaises[] = [sampleWithPartialData];
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing(
          onsIntercambioSinComOutrosPaisesCollection,
          onsIntercambioSinComOutrosPaises,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIntercambioSinComOutrosPaises);
      });

      it('should add only unique OnsIntercambioSinComOutrosPaises to an array', () => {
        const onsIntercambioSinComOutrosPaisesArray: IOnsIntercambioSinComOutrosPaises[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsIntercambioSinComOutrosPaisesCollection: IOnsIntercambioSinComOutrosPaises[] = [sampleWithRequiredData];
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing(
          onsIntercambioSinComOutrosPaisesCollection,
          ...onsIntercambioSinComOutrosPaisesArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises = sampleWithRequiredData;
        const onsIntercambioSinComOutrosPaises2: IOnsIntercambioSinComOutrosPaises = sampleWithPartialData;
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing(
          [],
          onsIntercambioSinComOutrosPaises,
          onsIntercambioSinComOutrosPaises2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIntercambioSinComOutrosPaises);
        expect(expectedResult).toContain(onsIntercambioSinComOutrosPaises2);
      });

      it('should accept null and undefined values', () => {
        const onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises = sampleWithRequiredData;
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing(
          [],
          null,
          onsIntercambioSinComOutrosPaises,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIntercambioSinComOutrosPaises);
      });

      it('should return initial array if no OnsIntercambioSinComOutrosPaises is added', () => {
        const onsIntercambioSinComOutrosPaisesCollection: IOnsIntercambioSinComOutrosPaises[] = [sampleWithRequiredData];
        expectedResult = service.addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing(
          onsIntercambioSinComOutrosPaisesCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIntercambioSinComOutrosPaisesCollection);
      });
    });

    describe('compareOnsIntercambioSinComOutrosPaises', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIntercambioSinComOutrosPaises(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 8173 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIntercambioSinComOutrosPaises(entity1, entity2);
        const compareResult2 = service.compareOnsIntercambioSinComOutrosPaises(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 8173 };
        const entity2 = { id: 30807 };

        const compareResult1 = service.compareOnsIntercambioSinComOutrosPaises(entity1, entity2);
        const compareResult2 = service.compareOnsIntercambioSinComOutrosPaises(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 8173 };
        const entity2 = { id: 8173 };

        const compareResult1 = service.compareOnsIntercambioSinComOutrosPaises(entity1, entity2);
        const compareResult2 = service.compareOnsIntercambioSinComOutrosPaises(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
