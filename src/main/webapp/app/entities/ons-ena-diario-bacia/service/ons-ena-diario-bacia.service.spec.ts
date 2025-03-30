import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../ons-ena-diario-bacia.test-samples';

import { OnsEnaDiarioBaciaService } from './ons-ena-diario-bacia.service';

const requireRestSample: IOnsEnaDiarioBacia = {
  ...sampleWithRequiredData,
};

describe('OnsEnaDiarioBacia Service', () => {
  let service: OnsEnaDiarioBaciaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEnaDiarioBacia | IOnsEnaDiarioBacia[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEnaDiarioBaciaService);
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

    it('should create a OnsEnaDiarioBacia', () => {
      const onsEnaDiarioBacia = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEnaDiarioBacia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEnaDiarioBacia', () => {
      const onsEnaDiarioBacia = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEnaDiarioBacia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEnaDiarioBacia', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEnaDiarioBacia', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEnaDiarioBacia', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEnaDiarioBacia', () => {
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

    describe('addOnsEnaDiarioBaciaToCollectionIfMissing', () => {
      it('should add a OnsEnaDiarioBacia to an empty array', () => {
        const onsEnaDiarioBacia: IOnsEnaDiarioBacia = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing([], onsEnaDiarioBacia);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioBacia);
      });

      it('should not add a OnsEnaDiarioBacia to an array that contains it', () => {
        const onsEnaDiarioBacia: IOnsEnaDiarioBacia = sampleWithRequiredData;
        const onsEnaDiarioBaciaCollection: IOnsEnaDiarioBacia[] = [
          {
            ...onsEnaDiarioBacia,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing(onsEnaDiarioBaciaCollection, onsEnaDiarioBacia);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEnaDiarioBacia to an array that doesn't contain it", () => {
        const onsEnaDiarioBacia: IOnsEnaDiarioBacia = sampleWithRequiredData;
        const onsEnaDiarioBaciaCollection: IOnsEnaDiarioBacia[] = [sampleWithPartialData];
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing(onsEnaDiarioBaciaCollection, onsEnaDiarioBacia);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioBacia);
      });

      it('should add only unique OnsEnaDiarioBacia to an array', () => {
        const onsEnaDiarioBaciaArray: IOnsEnaDiarioBacia[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsEnaDiarioBaciaCollection: IOnsEnaDiarioBacia[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing(onsEnaDiarioBaciaCollection, ...onsEnaDiarioBaciaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEnaDiarioBacia: IOnsEnaDiarioBacia = sampleWithRequiredData;
        const onsEnaDiarioBacia2: IOnsEnaDiarioBacia = sampleWithPartialData;
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing([], onsEnaDiarioBacia, onsEnaDiarioBacia2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioBacia);
        expect(expectedResult).toContain(onsEnaDiarioBacia2);
      });

      it('should accept null and undefined values', () => {
        const onsEnaDiarioBacia: IOnsEnaDiarioBacia = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing([], null, onsEnaDiarioBacia, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioBacia);
      });

      it('should return initial array if no OnsEnaDiarioBacia is added', () => {
        const onsEnaDiarioBaciaCollection: IOnsEnaDiarioBacia[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEnaDiarioBaciaToCollectionIfMissing(onsEnaDiarioBaciaCollection, undefined, null);
        expect(expectedResult).toEqual(onsEnaDiarioBaciaCollection);
      });
    });

    describe('compareOnsEnaDiarioBacia', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEnaDiarioBacia(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 732 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEnaDiarioBacia(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioBacia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 732 };
        const entity2 = { id: 23050 };

        const compareResult1 = service.compareOnsEnaDiarioBacia(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioBacia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 732 };
        const entity2 = { id: 732 };

        const compareResult1 = service.compareOnsEnaDiarioBacia(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioBacia(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
