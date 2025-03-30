import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-carga-energia-programada.test-samples';

import { OnsCargaEnergiaProgramadaService, RestOnsCargaEnergiaProgramada } from './ons-carga-energia-programada.service';

const requireRestSample: RestOnsCargaEnergiaProgramada = {
  ...sampleWithRequiredData,
  datReferencia: sampleWithRequiredData.datReferencia?.format(DATE_FORMAT),
  dinReferenciautc: sampleWithRequiredData.dinReferenciautc?.format(DATE_FORMAT),
};

describe('OnsCargaEnergiaProgramada Service', () => {
  let service: OnsCargaEnergiaProgramadaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCargaEnergiaProgramada | IOnsCargaEnergiaProgramada[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCargaEnergiaProgramadaService);
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

    it('should create a OnsCargaEnergiaProgramada', () => {
      const onsCargaEnergiaProgramada = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCargaEnergiaProgramada).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCargaEnergiaProgramada', () => {
      const onsCargaEnergiaProgramada = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCargaEnergiaProgramada).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCargaEnergiaProgramada', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCargaEnergiaProgramada', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCargaEnergiaProgramada', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCargaEnergiaProgramada', () => {
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

    describe('addOnsCargaEnergiaProgramadaToCollectionIfMissing', () => {
      it('should add a OnsCargaEnergiaProgramada to an empty array', () => {
        const onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada = sampleWithRequiredData;
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing([], onsCargaEnergiaProgramada);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCargaEnergiaProgramada);
      });

      it('should not add a OnsCargaEnergiaProgramada to an array that contains it', () => {
        const onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada = sampleWithRequiredData;
        const onsCargaEnergiaProgramadaCollection: IOnsCargaEnergiaProgramada[] = [
          {
            ...onsCargaEnergiaProgramada,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing(
          onsCargaEnergiaProgramadaCollection,
          onsCargaEnergiaProgramada,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCargaEnergiaProgramada to an array that doesn't contain it", () => {
        const onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada = sampleWithRequiredData;
        const onsCargaEnergiaProgramadaCollection: IOnsCargaEnergiaProgramada[] = [sampleWithPartialData];
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing(
          onsCargaEnergiaProgramadaCollection,
          onsCargaEnergiaProgramada,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCargaEnergiaProgramada);
      });

      it('should add only unique OnsCargaEnergiaProgramada to an array', () => {
        const onsCargaEnergiaProgramadaArray: IOnsCargaEnergiaProgramada[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsCargaEnergiaProgramadaCollection: IOnsCargaEnergiaProgramada[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing(
          onsCargaEnergiaProgramadaCollection,
          ...onsCargaEnergiaProgramadaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada = sampleWithRequiredData;
        const onsCargaEnergiaProgramada2: IOnsCargaEnergiaProgramada = sampleWithPartialData;
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing(
          [],
          onsCargaEnergiaProgramada,
          onsCargaEnergiaProgramada2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCargaEnergiaProgramada);
        expect(expectedResult).toContain(onsCargaEnergiaProgramada2);
      });

      it('should accept null and undefined values', () => {
        const onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada = sampleWithRequiredData;
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing([], null, onsCargaEnergiaProgramada, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCargaEnergiaProgramada);
      });

      it('should return initial array if no OnsCargaEnergiaProgramada is added', () => {
        const onsCargaEnergiaProgramadaCollection: IOnsCargaEnergiaProgramada[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCargaEnergiaProgramadaToCollectionIfMissing(onsCargaEnergiaProgramadaCollection, undefined, null);
        expect(expectedResult).toEqual(onsCargaEnergiaProgramadaCollection);
      });
    });

    describe('compareOnsCargaEnergiaProgramada', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCargaEnergiaProgramada(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 16894 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCargaEnergiaProgramada(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaProgramada(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 16894 };
        const entity2 = { id: 2126 };

        const compareResult1 = service.compareOnsCargaEnergiaProgramada(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaProgramada(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 16894 };
        const entity2 = { id: 16894 };

        const compareResult1 = service.compareOnsCargaEnergiaProgramada(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaProgramada(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
