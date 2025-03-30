import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../ons-cmo-semanal.test-samples';

import { OnsCmoSemanalService, RestOnsCmoSemanal } from './ons-cmo-semanal.service';

const requireRestSample: RestOnsCmoSemanal = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsCmoSemanal Service', () => {
  let service: OnsCmoSemanalService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCmoSemanal | IOnsCmoSemanal[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCmoSemanalService);
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

    it('should create a OnsCmoSemanal', () => {
      const onsCmoSemanal = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCmoSemanal).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCmoSemanal', () => {
      const onsCmoSemanal = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCmoSemanal).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCmoSemanal', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCmoSemanal', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCmoSemanal', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCmoSemanal', () => {
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

    describe('addOnsCmoSemanalToCollectionIfMissing', () => {
      it('should add a OnsCmoSemanal to an empty array', () => {
        const onsCmoSemanal: IOnsCmoSemanal = sampleWithRequiredData;
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing([], onsCmoSemanal);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCmoSemanal);
      });

      it('should not add a OnsCmoSemanal to an array that contains it', () => {
        const onsCmoSemanal: IOnsCmoSemanal = sampleWithRequiredData;
        const onsCmoSemanalCollection: IOnsCmoSemanal[] = [
          {
            ...onsCmoSemanal,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing(onsCmoSemanalCollection, onsCmoSemanal);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCmoSemanal to an array that doesn't contain it", () => {
        const onsCmoSemanal: IOnsCmoSemanal = sampleWithRequiredData;
        const onsCmoSemanalCollection: IOnsCmoSemanal[] = [sampleWithPartialData];
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing(onsCmoSemanalCollection, onsCmoSemanal);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCmoSemanal);
      });

      it('should add only unique OnsCmoSemanal to an array', () => {
        const onsCmoSemanalArray: IOnsCmoSemanal[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsCmoSemanalCollection: IOnsCmoSemanal[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing(onsCmoSemanalCollection, ...onsCmoSemanalArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCmoSemanal: IOnsCmoSemanal = sampleWithRequiredData;
        const onsCmoSemanal2: IOnsCmoSemanal = sampleWithPartialData;
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing([], onsCmoSemanal, onsCmoSemanal2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCmoSemanal);
        expect(expectedResult).toContain(onsCmoSemanal2);
      });

      it('should accept null and undefined values', () => {
        const onsCmoSemanal: IOnsCmoSemanal = sampleWithRequiredData;
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing([], null, onsCmoSemanal, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCmoSemanal);
      });

      it('should return initial array if no OnsCmoSemanal is added', () => {
        const onsCmoSemanalCollection: IOnsCmoSemanal[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCmoSemanalToCollectionIfMissing(onsCmoSemanalCollection, undefined, null);
        expect(expectedResult).toEqual(onsCmoSemanalCollection);
      });
    });

    describe('compareOnsCmoSemanal', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCmoSemanal(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 18466 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCmoSemanal(entity1, entity2);
        const compareResult2 = service.compareOnsCmoSemanal(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 18466 };
        const entity2 = { id: 2156 };

        const compareResult1 = service.compareOnsCmoSemanal(entity1, entity2);
        const compareResult2 = service.compareOnsCmoSemanal(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 18466 };
        const entity2 = { id: 18466 };

        const compareResult1 = service.compareOnsCmoSemanal(entity1, entity2);
        const compareResult2 = service.compareOnsCmoSemanal(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
