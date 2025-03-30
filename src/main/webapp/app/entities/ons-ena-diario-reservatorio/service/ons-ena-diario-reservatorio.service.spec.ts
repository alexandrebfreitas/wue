import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ena-diario-reservatorio.test-samples';

import { OnsEnaDiarioReservatorioService } from './ons-ena-diario-reservatorio.service';

const requireRestSample: IOnsEnaDiarioReservatorio = {
  ...sampleWithRequiredData,
};

describe('OnsEnaDiarioReservatorio Service', () => {
  let service: OnsEnaDiarioReservatorioService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEnaDiarioReservatorio | IOnsEnaDiarioReservatorio[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEnaDiarioReservatorioService);
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

    it('should create a OnsEnaDiarioReservatorio', () => {
      const onsEnaDiarioReservatorio = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEnaDiarioReservatorio).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEnaDiarioReservatorio', () => {
      const onsEnaDiarioReservatorio = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEnaDiarioReservatorio).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEnaDiarioReservatorio', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEnaDiarioReservatorio', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEnaDiarioReservatorio', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEnaDiarioReservatorio', () => {
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

    describe('addOnsEnaDiarioReservatorioToCollectionIfMissing', () => {
      it('should add a OnsEnaDiarioReservatorio to an empty array', () => {
        const onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing([], onsEnaDiarioReservatorio);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioReservatorio);
      });

      it('should not add a OnsEnaDiarioReservatorio to an array that contains it', () => {
        const onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio = sampleWithRequiredData;
        const onsEnaDiarioReservatorioCollection: IOnsEnaDiarioReservatorio[] = [
          {
            ...onsEnaDiarioReservatorio,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing(
          onsEnaDiarioReservatorioCollection,
          onsEnaDiarioReservatorio,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEnaDiarioReservatorio to an array that doesn't contain it", () => {
        const onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio = sampleWithRequiredData;
        const onsEnaDiarioReservatorioCollection: IOnsEnaDiarioReservatorio[] = [sampleWithPartialData];
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing(
          onsEnaDiarioReservatorioCollection,
          onsEnaDiarioReservatorio,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioReservatorio);
      });

      it('should add only unique OnsEnaDiarioReservatorio to an array', () => {
        const onsEnaDiarioReservatorioArray: IOnsEnaDiarioReservatorio[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsEnaDiarioReservatorioCollection: IOnsEnaDiarioReservatorio[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing(
          onsEnaDiarioReservatorioCollection,
          ...onsEnaDiarioReservatorioArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio = sampleWithRequiredData;
        const onsEnaDiarioReservatorio2: IOnsEnaDiarioReservatorio = sampleWithPartialData;
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing([], onsEnaDiarioReservatorio, onsEnaDiarioReservatorio2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioReservatorio);
        expect(expectedResult).toContain(onsEnaDiarioReservatorio2);
      });

      it('should accept null and undefined values', () => {
        const onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing([], null, onsEnaDiarioReservatorio, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioReservatorio);
      });

      it('should return initial array if no OnsEnaDiarioReservatorio is added', () => {
        const onsEnaDiarioReservatorioCollection: IOnsEnaDiarioReservatorio[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEnaDiarioReservatorioToCollectionIfMissing(onsEnaDiarioReservatorioCollection, undefined, null);
        expect(expectedResult).toEqual(onsEnaDiarioReservatorioCollection);
      });
    });

    describe('compareOnsEnaDiarioReservatorio', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEnaDiarioReservatorio(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 28943 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEnaDiarioReservatorio(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioReservatorio(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 28943 };
        const entity2 = { id: 3628 };

        const compareResult1 = service.compareOnsEnaDiarioReservatorio(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioReservatorio(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 28943 };
        const entity2 = { id: 28943 };

        const compareResult1 = service.compareOnsEnaDiarioReservatorio(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioReservatorio(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
