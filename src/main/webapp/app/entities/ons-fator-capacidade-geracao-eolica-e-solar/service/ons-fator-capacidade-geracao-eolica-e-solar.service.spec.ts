import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-fator-capacidade-geracao-eolica-e-solar.test-samples';

import {
  OnsFatorCapacidadeGeracaoEolicaESolarService,
  RestOnsFatorCapacidadeGeracaoEolicaESolar,
} from './ons-fator-capacidade-geracao-eolica-e-solar.service';

const requireRestSample: RestOnsFatorCapacidadeGeracaoEolicaESolar = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsFatorCapacidadeGeracaoEolicaESolar Service', () => {
  let service: OnsFatorCapacidadeGeracaoEolicaESolarService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsFatorCapacidadeGeracaoEolicaESolar | IOnsFatorCapacidadeGeracaoEolicaESolar[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsFatorCapacidadeGeracaoEolicaESolarService);
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

    it('should create a OnsFatorCapacidadeGeracaoEolicaESolar', () => {
      const onsFatorCapacidadeGeracaoEolicaESolar = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsFatorCapacidadeGeracaoEolicaESolar).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsFatorCapacidadeGeracaoEolicaESolar', () => {
      const onsFatorCapacidadeGeracaoEolicaESolar = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsFatorCapacidadeGeracaoEolicaESolar).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsFatorCapacidadeGeracaoEolicaESolar', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsFatorCapacidadeGeracaoEolicaESolar', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsFatorCapacidadeGeracaoEolicaESolar', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsFatorCapacidadeGeracaoEolicaESolar', () => {
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

    describe('addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing', () => {
      it('should add a OnsFatorCapacidadeGeracaoEolicaESolar to an empty array', () => {
        const onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar = sampleWithRequiredData;
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing([], onsFatorCapacidadeGeracaoEolicaESolar);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsFatorCapacidadeGeracaoEolicaESolar);
      });

      it('should not add a OnsFatorCapacidadeGeracaoEolicaESolar to an array that contains it', () => {
        const onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar = sampleWithRequiredData;
        const onsFatorCapacidadeGeracaoEolicaESolarCollection: IOnsFatorCapacidadeGeracaoEolicaESolar[] = [
          {
            ...onsFatorCapacidadeGeracaoEolicaESolar,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing(
          onsFatorCapacidadeGeracaoEolicaESolarCollection,
          onsFatorCapacidadeGeracaoEolicaESolar,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsFatorCapacidadeGeracaoEolicaESolar to an array that doesn't contain it", () => {
        const onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar = sampleWithRequiredData;
        const onsFatorCapacidadeGeracaoEolicaESolarCollection: IOnsFatorCapacidadeGeracaoEolicaESolar[] = [sampleWithPartialData];
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing(
          onsFatorCapacidadeGeracaoEolicaESolarCollection,
          onsFatorCapacidadeGeracaoEolicaESolar,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsFatorCapacidadeGeracaoEolicaESolar);
      });

      it('should add only unique OnsFatorCapacidadeGeracaoEolicaESolar to an array', () => {
        const onsFatorCapacidadeGeracaoEolicaESolarArray: IOnsFatorCapacidadeGeracaoEolicaESolar[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsFatorCapacidadeGeracaoEolicaESolarCollection: IOnsFatorCapacidadeGeracaoEolicaESolar[] = [sampleWithRequiredData];
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing(
          onsFatorCapacidadeGeracaoEolicaESolarCollection,
          ...onsFatorCapacidadeGeracaoEolicaESolarArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar = sampleWithRequiredData;
        const onsFatorCapacidadeGeracaoEolicaESolar2: IOnsFatorCapacidadeGeracaoEolicaESolar = sampleWithPartialData;
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing(
          [],
          onsFatorCapacidadeGeracaoEolicaESolar,
          onsFatorCapacidadeGeracaoEolicaESolar2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsFatorCapacidadeGeracaoEolicaESolar);
        expect(expectedResult).toContain(onsFatorCapacidadeGeracaoEolicaESolar2);
      });

      it('should accept null and undefined values', () => {
        const onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar = sampleWithRequiredData;
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing(
          [],
          null,
          onsFatorCapacidadeGeracaoEolicaESolar,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsFatorCapacidadeGeracaoEolicaESolar);
      });

      it('should return initial array if no OnsFatorCapacidadeGeracaoEolicaESolar is added', () => {
        const onsFatorCapacidadeGeracaoEolicaESolarCollection: IOnsFatorCapacidadeGeracaoEolicaESolar[] = [sampleWithRequiredData];
        expectedResult = service.addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing(
          onsFatorCapacidadeGeracaoEolicaESolarCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsFatorCapacidadeGeracaoEolicaESolarCollection);
      });
    });

    describe('compareOnsFatorCapacidadeGeracaoEolicaESolar', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 30505 };
        const entity2 = null;

        const compareResult1 = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity1, entity2);
        const compareResult2 = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 30505 };
        const entity2 = { id: 16288 };

        const compareResult1 = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity1, entity2);
        const compareResult2 = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 30505 };
        const entity2 = { id: 30505 };

        const compareResult1 = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity1, entity2);
        const compareResult2 = service.compareOnsFatorCapacidadeGeracaoEolicaESolar(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
