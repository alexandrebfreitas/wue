import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-taxas-teifa-e-teip.test-samples';

import { OnsTaxasTeifaETeipService, RestOnsTaxasTeifaETeip } from './ons-taxas-teifa-e-teip.service';

const requireRestSample: RestOnsTaxasTeifaETeip = {
  ...sampleWithRequiredData,
  dinMes: sampleWithRequiredData.dinMes?.format(DATE_FORMAT),
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsTaxasTeifaETeip Service', () => {
  let service: OnsTaxasTeifaETeipService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsTaxasTeifaETeip | IOnsTaxasTeifaETeip[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsTaxasTeifaETeipService);
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

    it('should create a OnsTaxasTeifaETeip', () => {
      const onsTaxasTeifaETeip = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsTaxasTeifaETeip).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsTaxasTeifaETeip', () => {
      const onsTaxasTeifaETeip = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsTaxasTeifaETeip).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsTaxasTeifaETeip', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsTaxasTeifaETeip', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsTaxasTeifaETeip', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsTaxasTeifaETeip', () => {
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

    describe('addOnsTaxasTeifaETeipToCollectionIfMissing', () => {
      it('should add a OnsTaxasTeifaETeip to an empty array', () => {
        const onsTaxasTeifaETeip: IOnsTaxasTeifaETeip = sampleWithRequiredData;
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing([], onsTaxasTeifaETeip);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsTaxasTeifaETeip);
      });

      it('should not add a OnsTaxasTeifaETeip to an array that contains it', () => {
        const onsTaxasTeifaETeip: IOnsTaxasTeifaETeip = sampleWithRequiredData;
        const onsTaxasTeifaETeipCollection: IOnsTaxasTeifaETeip[] = [
          {
            ...onsTaxasTeifaETeip,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing(onsTaxasTeifaETeipCollection, onsTaxasTeifaETeip);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsTaxasTeifaETeip to an array that doesn't contain it", () => {
        const onsTaxasTeifaETeip: IOnsTaxasTeifaETeip = sampleWithRequiredData;
        const onsTaxasTeifaETeipCollection: IOnsTaxasTeifaETeip[] = [sampleWithPartialData];
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing(onsTaxasTeifaETeipCollection, onsTaxasTeifaETeip);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsTaxasTeifaETeip);
      });

      it('should add only unique OnsTaxasTeifaETeip to an array', () => {
        const onsTaxasTeifaETeipArray: IOnsTaxasTeifaETeip[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsTaxasTeifaETeipCollection: IOnsTaxasTeifaETeip[] = [sampleWithRequiredData];
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing(onsTaxasTeifaETeipCollection, ...onsTaxasTeifaETeipArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsTaxasTeifaETeip: IOnsTaxasTeifaETeip = sampleWithRequiredData;
        const onsTaxasTeifaETeip2: IOnsTaxasTeifaETeip = sampleWithPartialData;
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing([], onsTaxasTeifaETeip, onsTaxasTeifaETeip2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsTaxasTeifaETeip);
        expect(expectedResult).toContain(onsTaxasTeifaETeip2);
      });

      it('should accept null and undefined values', () => {
        const onsTaxasTeifaETeip: IOnsTaxasTeifaETeip = sampleWithRequiredData;
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing([], null, onsTaxasTeifaETeip, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsTaxasTeifaETeip);
      });

      it('should return initial array if no OnsTaxasTeifaETeip is added', () => {
        const onsTaxasTeifaETeipCollection: IOnsTaxasTeifaETeip[] = [sampleWithRequiredData];
        expectedResult = service.addOnsTaxasTeifaETeipToCollectionIfMissing(onsTaxasTeifaETeipCollection, undefined, null);
        expect(expectedResult).toEqual(onsTaxasTeifaETeipCollection);
      });
    });

    describe('compareOnsTaxasTeifaETeip', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsTaxasTeifaETeip(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 9188 };
        const entity2 = null;

        const compareResult1 = service.compareOnsTaxasTeifaETeip(entity1, entity2);
        const compareResult2 = service.compareOnsTaxasTeifaETeip(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 9188 };
        const entity2 = { id: 16755 };

        const compareResult1 = service.compareOnsTaxasTeifaETeip(entity1, entity2);
        const compareResult2 = service.compareOnsTaxasTeifaETeip(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 9188 };
        const entity2 = { id: 9188 };

        const compareResult1 = service.compareOnsTaxasTeifaETeip(entity1, entity2);
        const compareResult2 = service.compareOnsTaxasTeifaETeip(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
