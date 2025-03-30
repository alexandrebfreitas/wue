import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsReservatorios } from '../ons-reservatorios.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../ons-reservatorios.test-samples';

import { OnsReservatoriosService, RestOnsReservatorios } from './ons-reservatorios.service';

const requireRestSample: RestOnsReservatorios = {
  ...sampleWithRequiredData,
  datEntrada: sampleWithRequiredData.datEntrada?.format(DATE_FORMAT),
};

describe('OnsReservatorios Service', () => {
  let service: OnsReservatoriosService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsReservatorios | IOnsReservatorios[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsReservatoriosService);
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

    it('should create a OnsReservatorios', () => {
      const onsReservatorios = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsReservatorios).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsReservatorios', () => {
      const onsReservatorios = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsReservatorios).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsReservatorios', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsReservatorios', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsReservatorios', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsReservatorios', () => {
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

    describe('addOnsReservatoriosToCollectionIfMissing', () => {
      it('should add a OnsReservatorios to an empty array', () => {
        const onsReservatorios: IOnsReservatorios = sampleWithRequiredData;
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing([], onsReservatorios);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsReservatorios);
      });

      it('should not add a OnsReservatorios to an array that contains it', () => {
        const onsReservatorios: IOnsReservatorios = sampleWithRequiredData;
        const onsReservatoriosCollection: IOnsReservatorios[] = [
          {
            ...onsReservatorios,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing(onsReservatoriosCollection, onsReservatorios);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsReservatorios to an array that doesn't contain it", () => {
        const onsReservatorios: IOnsReservatorios = sampleWithRequiredData;
        const onsReservatoriosCollection: IOnsReservatorios[] = [sampleWithPartialData];
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing(onsReservatoriosCollection, onsReservatorios);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsReservatorios);
      });

      it('should add only unique OnsReservatorios to an array', () => {
        const onsReservatoriosArray: IOnsReservatorios[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsReservatoriosCollection: IOnsReservatorios[] = [sampleWithRequiredData];
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing(onsReservatoriosCollection, ...onsReservatoriosArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsReservatorios: IOnsReservatorios = sampleWithRequiredData;
        const onsReservatorios2: IOnsReservatorios = sampleWithPartialData;
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing([], onsReservatorios, onsReservatorios2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsReservatorios);
        expect(expectedResult).toContain(onsReservatorios2);
      });

      it('should accept null and undefined values', () => {
        const onsReservatorios: IOnsReservatorios = sampleWithRequiredData;
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing([], null, onsReservatorios, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsReservatorios);
      });

      it('should return initial array if no OnsReservatorios is added', () => {
        const onsReservatoriosCollection: IOnsReservatorios[] = [sampleWithRequiredData];
        expectedResult = service.addOnsReservatoriosToCollectionIfMissing(onsReservatoriosCollection, undefined, null);
        expect(expectedResult).toEqual(onsReservatoriosCollection);
      });
    });

    describe('compareOnsReservatorios', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsReservatorios(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 25623 };
        const entity2 = null;

        const compareResult1 = service.compareOnsReservatorios(entity1, entity2);
        const compareResult2 = service.compareOnsReservatorios(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 25623 };
        const entity2 = { id: 21014 };

        const compareResult1 = service.compareOnsReservatorios(entity1, entity2);
        const compareResult2 = service.compareOnsReservatorios(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 25623 };
        const entity2 = { id: 25623 };

        const compareResult1 = service.compareOnsReservatorios(entity1, entity2);
        const compareResult2 = service.compareOnsReservatorios(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
