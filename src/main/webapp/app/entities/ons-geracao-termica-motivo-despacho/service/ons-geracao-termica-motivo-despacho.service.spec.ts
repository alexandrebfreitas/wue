import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-geracao-termica-motivo-despacho.test-samples';

import { OnsGeracaoTermicaMotivoDespachoService, RestOnsGeracaoTermicaMotivoDespacho } from './ons-geracao-termica-motivo-despacho.service';

const requireRestSample: RestOnsGeracaoTermicaMotivoDespacho = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsGeracaoTermicaMotivoDespacho Service', () => {
  let service: OnsGeracaoTermicaMotivoDespachoService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsGeracaoTermicaMotivoDespacho | IOnsGeracaoTermicaMotivoDespacho[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsGeracaoTermicaMotivoDespachoService);
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

    it('should create a OnsGeracaoTermicaMotivoDespacho', () => {
      const onsGeracaoTermicaMotivoDespacho = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsGeracaoTermicaMotivoDespacho).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsGeracaoTermicaMotivoDespacho', () => {
      const onsGeracaoTermicaMotivoDespacho = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsGeracaoTermicaMotivoDespacho).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsGeracaoTermicaMotivoDespacho', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsGeracaoTermicaMotivoDespacho', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsGeracaoTermicaMotivoDespacho', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsGeracaoTermicaMotivoDespacho', () => {
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

    describe('addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing', () => {
      it('should add a OnsGeracaoTermicaMotivoDespacho to an empty array', () => {
        const onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho = sampleWithRequiredData;
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing([], onsGeracaoTermicaMotivoDespacho);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsGeracaoTermicaMotivoDespacho);
      });

      it('should not add a OnsGeracaoTermicaMotivoDespacho to an array that contains it', () => {
        const onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho = sampleWithRequiredData;
        const onsGeracaoTermicaMotivoDespachoCollection: IOnsGeracaoTermicaMotivoDespacho[] = [
          {
            ...onsGeracaoTermicaMotivoDespacho,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing(
          onsGeracaoTermicaMotivoDespachoCollection,
          onsGeracaoTermicaMotivoDespacho,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsGeracaoTermicaMotivoDespacho to an array that doesn't contain it", () => {
        const onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho = sampleWithRequiredData;
        const onsGeracaoTermicaMotivoDespachoCollection: IOnsGeracaoTermicaMotivoDespacho[] = [sampleWithPartialData];
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing(
          onsGeracaoTermicaMotivoDespachoCollection,
          onsGeracaoTermicaMotivoDespacho,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsGeracaoTermicaMotivoDespacho);
      });

      it('should add only unique OnsGeracaoTermicaMotivoDespacho to an array', () => {
        const onsGeracaoTermicaMotivoDespachoArray: IOnsGeracaoTermicaMotivoDespacho[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsGeracaoTermicaMotivoDespachoCollection: IOnsGeracaoTermicaMotivoDespacho[] = [sampleWithRequiredData];
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing(
          onsGeracaoTermicaMotivoDespachoCollection,
          ...onsGeracaoTermicaMotivoDespachoArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho = sampleWithRequiredData;
        const onsGeracaoTermicaMotivoDespacho2: IOnsGeracaoTermicaMotivoDespacho = sampleWithPartialData;
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing(
          [],
          onsGeracaoTermicaMotivoDespacho,
          onsGeracaoTermicaMotivoDespacho2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsGeracaoTermicaMotivoDespacho);
        expect(expectedResult).toContain(onsGeracaoTermicaMotivoDespacho2);
      });

      it('should accept null and undefined values', () => {
        const onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho = sampleWithRequiredData;
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing(
          [],
          null,
          onsGeracaoTermicaMotivoDespacho,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsGeracaoTermicaMotivoDespacho);
      });

      it('should return initial array if no OnsGeracaoTermicaMotivoDespacho is added', () => {
        const onsGeracaoTermicaMotivoDespachoCollection: IOnsGeracaoTermicaMotivoDespacho[] = [sampleWithRequiredData];
        expectedResult = service.addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing(
          onsGeracaoTermicaMotivoDespachoCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsGeracaoTermicaMotivoDespachoCollection);
      });
    });

    describe('compareOnsGeracaoTermicaMotivoDespacho', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsGeracaoTermicaMotivoDespacho(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 18788 };
        const entity2 = null;

        const compareResult1 = service.compareOnsGeracaoTermicaMotivoDespacho(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoTermicaMotivoDespacho(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 18788 };
        const entity2 = { id: 29605 };

        const compareResult1 = service.compareOnsGeracaoTermicaMotivoDespacho(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoTermicaMotivoDespacho(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 18788 };
        const entity2 = { id: 18788 };

        const compareResult1 = service.compareOnsGeracaoTermicaMotivoDespacho(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoTermicaMotivoDespacho(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
