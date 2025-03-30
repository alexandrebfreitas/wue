import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ear-diario-reservatorio.test-samples';

import { OnsEarDiarioReservatorioService, RestOnsEarDiarioReservatorio } from './ons-ear-diario-reservatorio.service';

const requireRestSample: RestOnsEarDiarioReservatorio = {
  ...sampleWithRequiredData,
  earData: sampleWithRequiredData.earData?.format(DATE_FORMAT),
};

describe('OnsEarDiarioReservatorio Service', () => {
  let service: OnsEarDiarioReservatorioService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEarDiarioReservatorio | IOnsEarDiarioReservatorio[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEarDiarioReservatorioService);
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

    it('should create a OnsEarDiarioReservatorio', () => {
      const onsEarDiarioReservatorio = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEarDiarioReservatorio).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEarDiarioReservatorio', () => {
      const onsEarDiarioReservatorio = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEarDiarioReservatorio).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEarDiarioReservatorio', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEarDiarioReservatorio', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEarDiarioReservatorio', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEarDiarioReservatorio', () => {
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

    describe('addOnsEarDiarioReservatorioToCollectionIfMissing', () => {
      it('should add a OnsEarDiarioReservatorio to an empty array', () => {
        const onsEarDiarioReservatorio: IOnsEarDiarioReservatorio = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing([], onsEarDiarioReservatorio);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioReservatorio);
      });

      it('should not add a OnsEarDiarioReservatorio to an array that contains it', () => {
        const onsEarDiarioReservatorio: IOnsEarDiarioReservatorio = sampleWithRequiredData;
        const onsEarDiarioReservatorioCollection: IOnsEarDiarioReservatorio[] = [
          {
            ...onsEarDiarioReservatorio,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing(
          onsEarDiarioReservatorioCollection,
          onsEarDiarioReservatorio,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEarDiarioReservatorio to an array that doesn't contain it", () => {
        const onsEarDiarioReservatorio: IOnsEarDiarioReservatorio = sampleWithRequiredData;
        const onsEarDiarioReservatorioCollection: IOnsEarDiarioReservatorio[] = [sampleWithPartialData];
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing(
          onsEarDiarioReservatorioCollection,
          onsEarDiarioReservatorio,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioReservatorio);
      });

      it('should add only unique OnsEarDiarioReservatorio to an array', () => {
        const onsEarDiarioReservatorioArray: IOnsEarDiarioReservatorio[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsEarDiarioReservatorioCollection: IOnsEarDiarioReservatorio[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing(
          onsEarDiarioReservatorioCollection,
          ...onsEarDiarioReservatorioArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEarDiarioReservatorio: IOnsEarDiarioReservatorio = sampleWithRequiredData;
        const onsEarDiarioReservatorio2: IOnsEarDiarioReservatorio = sampleWithPartialData;
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing([], onsEarDiarioReservatorio, onsEarDiarioReservatorio2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioReservatorio);
        expect(expectedResult).toContain(onsEarDiarioReservatorio2);
      });

      it('should accept null and undefined values', () => {
        const onsEarDiarioReservatorio: IOnsEarDiarioReservatorio = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing([], null, onsEarDiarioReservatorio, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioReservatorio);
      });

      it('should return initial array if no OnsEarDiarioReservatorio is added', () => {
        const onsEarDiarioReservatorioCollection: IOnsEarDiarioReservatorio[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEarDiarioReservatorioToCollectionIfMissing(onsEarDiarioReservatorioCollection, undefined, null);
        expect(expectedResult).toEqual(onsEarDiarioReservatorioCollection);
      });
    });

    describe('compareOnsEarDiarioReservatorio', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEarDiarioReservatorio(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 29357 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEarDiarioReservatorio(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioReservatorio(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 29357 };
        const entity2 = { id: 4681 };

        const compareResult1 = service.compareOnsEarDiarioReservatorio(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioReservatorio(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 29357 };
        const entity2 = { id: 29357 };

        const compareResult1 = service.compareOnsEarDiarioReservatorio(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioReservatorio(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
