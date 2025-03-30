import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-carga-energia-mensal.test-samples';

import { OnsCargaEnergiaMensalService, RestOnsCargaEnergiaMensal } from './ons-carga-energia-mensal.service';

const requireRestSample: RestOnsCargaEnergiaMensal = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsCargaEnergiaMensal Service', () => {
  let service: OnsCargaEnergiaMensalService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCargaEnergiaMensal | IOnsCargaEnergiaMensal[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCargaEnergiaMensalService);
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

    it('should create a OnsCargaEnergiaMensal', () => {
      const onsCargaEnergiaMensal = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCargaEnergiaMensal).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCargaEnergiaMensal', () => {
      const onsCargaEnergiaMensal = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCargaEnergiaMensal).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCargaEnergiaMensal', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCargaEnergiaMensal', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCargaEnergiaMensal', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCargaEnergiaMensal', () => {
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

    describe('addOnsCargaEnergiaMensalToCollectionIfMissing', () => {
      it('should add a OnsCargaEnergiaMensal to an empty array', () => {
        const onsCargaEnergiaMensal: IOnsCargaEnergiaMensal = sampleWithRequiredData;
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing([], onsCargaEnergiaMensal);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCargaEnergiaMensal);
      });

      it('should not add a OnsCargaEnergiaMensal to an array that contains it', () => {
        const onsCargaEnergiaMensal: IOnsCargaEnergiaMensal = sampleWithRequiredData;
        const onsCargaEnergiaMensalCollection: IOnsCargaEnergiaMensal[] = [
          {
            ...onsCargaEnergiaMensal,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing(onsCargaEnergiaMensalCollection, onsCargaEnergiaMensal);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCargaEnergiaMensal to an array that doesn't contain it", () => {
        const onsCargaEnergiaMensal: IOnsCargaEnergiaMensal = sampleWithRequiredData;
        const onsCargaEnergiaMensalCollection: IOnsCargaEnergiaMensal[] = [sampleWithPartialData];
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing(onsCargaEnergiaMensalCollection, onsCargaEnergiaMensal);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCargaEnergiaMensal);
      });

      it('should add only unique OnsCargaEnergiaMensal to an array', () => {
        const onsCargaEnergiaMensalArray: IOnsCargaEnergiaMensal[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsCargaEnergiaMensalCollection: IOnsCargaEnergiaMensal[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing(
          onsCargaEnergiaMensalCollection,
          ...onsCargaEnergiaMensalArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCargaEnergiaMensal: IOnsCargaEnergiaMensal = sampleWithRequiredData;
        const onsCargaEnergiaMensal2: IOnsCargaEnergiaMensal = sampleWithPartialData;
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing([], onsCargaEnergiaMensal, onsCargaEnergiaMensal2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCargaEnergiaMensal);
        expect(expectedResult).toContain(onsCargaEnergiaMensal2);
      });

      it('should accept null and undefined values', () => {
        const onsCargaEnergiaMensal: IOnsCargaEnergiaMensal = sampleWithRequiredData;
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing([], null, onsCargaEnergiaMensal, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCargaEnergiaMensal);
      });

      it('should return initial array if no OnsCargaEnergiaMensal is added', () => {
        const onsCargaEnergiaMensalCollection: IOnsCargaEnergiaMensal[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCargaEnergiaMensalToCollectionIfMissing(onsCargaEnergiaMensalCollection, undefined, null);
        expect(expectedResult).toEqual(onsCargaEnergiaMensalCollection);
      });
    });

    describe('compareOnsCargaEnergiaMensal', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCargaEnergiaMensal(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 16554 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCargaEnergiaMensal(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaMensal(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 16554 };
        const entity2 = { id: 15863 };

        const compareResult1 = service.compareOnsCargaEnergiaMensal(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaMensal(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 16554 };
        const entity2 = { id: 16554 };

        const compareResult1 = service.compareOnsCargaEnergiaMensal(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaMensal(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
