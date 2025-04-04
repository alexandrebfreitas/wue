import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../ons-ear-diario-bacia.test-samples';

import { OnsEarDiarioBaciaService, RestOnsEarDiarioBacia } from './ons-ear-diario-bacia.service';

const requireRestSample: RestOnsEarDiarioBacia = {
  ...sampleWithRequiredData,
  earData: sampleWithRequiredData.earData?.format(DATE_FORMAT),
};

describe('OnsEarDiarioBacia Service', () => {
  let service: OnsEarDiarioBaciaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEarDiarioBacia | IOnsEarDiarioBacia[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEarDiarioBaciaService);
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

    it('should create a OnsEarDiarioBacia', () => {
      const onsEarDiarioBacia = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEarDiarioBacia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEarDiarioBacia', () => {
      const onsEarDiarioBacia = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEarDiarioBacia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEarDiarioBacia', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEarDiarioBacia', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEarDiarioBacia', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEarDiarioBacia', () => {
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

    describe('addOnsEarDiarioBaciaToCollectionIfMissing', () => {
      it('should add a OnsEarDiarioBacia to an empty array', () => {
        const onsEarDiarioBacia: IOnsEarDiarioBacia = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing([], onsEarDiarioBacia);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioBacia);
      });

      it('should not add a OnsEarDiarioBacia to an array that contains it', () => {
        const onsEarDiarioBacia: IOnsEarDiarioBacia = sampleWithRequiredData;
        const onsEarDiarioBaciaCollection: IOnsEarDiarioBacia[] = [
          {
            ...onsEarDiarioBacia,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing(onsEarDiarioBaciaCollection, onsEarDiarioBacia);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEarDiarioBacia to an array that doesn't contain it", () => {
        const onsEarDiarioBacia: IOnsEarDiarioBacia = sampleWithRequiredData;
        const onsEarDiarioBaciaCollection: IOnsEarDiarioBacia[] = [sampleWithPartialData];
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing(onsEarDiarioBaciaCollection, onsEarDiarioBacia);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioBacia);
      });

      it('should add only unique OnsEarDiarioBacia to an array', () => {
        const onsEarDiarioBaciaArray: IOnsEarDiarioBacia[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsEarDiarioBaciaCollection: IOnsEarDiarioBacia[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing(onsEarDiarioBaciaCollection, ...onsEarDiarioBaciaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEarDiarioBacia: IOnsEarDiarioBacia = sampleWithRequiredData;
        const onsEarDiarioBacia2: IOnsEarDiarioBacia = sampleWithPartialData;
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing([], onsEarDiarioBacia, onsEarDiarioBacia2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioBacia);
        expect(expectedResult).toContain(onsEarDiarioBacia2);
      });

      it('should accept null and undefined values', () => {
        const onsEarDiarioBacia: IOnsEarDiarioBacia = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing([], null, onsEarDiarioBacia, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioBacia);
      });

      it('should return initial array if no OnsEarDiarioBacia is added', () => {
        const onsEarDiarioBaciaCollection: IOnsEarDiarioBacia[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEarDiarioBaciaToCollectionIfMissing(onsEarDiarioBaciaCollection, undefined, null);
        expect(expectedResult).toEqual(onsEarDiarioBaciaCollection);
      });
    });

    describe('compareOnsEarDiarioBacia', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEarDiarioBacia(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 30574 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEarDiarioBacia(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioBacia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 30574 };
        const entity2 = { id: 2174 };

        const compareResult1 = service.compareOnsEarDiarioBacia(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioBacia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 30574 };
        const entity2 = { id: 30574 };

        const compareResult1 = service.compareOnsEarDiarioBacia(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioBacia(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
