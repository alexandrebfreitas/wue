import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-carga-energia-verificada.test-samples';

import { OnsCargaEnergiaVerificadaService, RestOnsCargaEnergiaVerificada } from './ons-carga-energia-verificada.service';

const requireRestSample: RestOnsCargaEnergiaVerificada = {
  ...sampleWithRequiredData,
  datReferencia: sampleWithRequiredData.datReferencia?.format(DATE_FORMAT),
  dinReferenciautc: sampleWithRequiredData.dinReferenciautc?.format(DATE_FORMAT),
};

describe('OnsCargaEnergiaVerificada Service', () => {
  let service: OnsCargaEnergiaVerificadaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCargaEnergiaVerificada | IOnsCargaEnergiaVerificada[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCargaEnergiaVerificadaService);
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

    it('should create a OnsCargaEnergiaVerificada', () => {
      const onsCargaEnergiaVerificada = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCargaEnergiaVerificada).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCargaEnergiaVerificada', () => {
      const onsCargaEnergiaVerificada = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCargaEnergiaVerificada).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCargaEnergiaVerificada', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCargaEnergiaVerificada', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCargaEnergiaVerificada', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCargaEnergiaVerificada', () => {
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

    describe('addOnsCargaEnergiaVerificadaToCollectionIfMissing', () => {
      it('should add a OnsCargaEnergiaVerificada to an empty array', () => {
        const onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada = sampleWithRequiredData;
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing([], onsCargaEnergiaVerificada);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCargaEnergiaVerificada);
      });

      it('should not add a OnsCargaEnergiaVerificada to an array that contains it', () => {
        const onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada = sampleWithRequiredData;
        const onsCargaEnergiaVerificadaCollection: IOnsCargaEnergiaVerificada[] = [
          {
            ...onsCargaEnergiaVerificada,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing(
          onsCargaEnergiaVerificadaCollection,
          onsCargaEnergiaVerificada,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCargaEnergiaVerificada to an array that doesn't contain it", () => {
        const onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada = sampleWithRequiredData;
        const onsCargaEnergiaVerificadaCollection: IOnsCargaEnergiaVerificada[] = [sampleWithPartialData];
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing(
          onsCargaEnergiaVerificadaCollection,
          onsCargaEnergiaVerificada,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCargaEnergiaVerificada);
      });

      it('should add only unique OnsCargaEnergiaVerificada to an array', () => {
        const onsCargaEnergiaVerificadaArray: IOnsCargaEnergiaVerificada[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsCargaEnergiaVerificadaCollection: IOnsCargaEnergiaVerificada[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing(
          onsCargaEnergiaVerificadaCollection,
          ...onsCargaEnergiaVerificadaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada = sampleWithRequiredData;
        const onsCargaEnergiaVerificada2: IOnsCargaEnergiaVerificada = sampleWithPartialData;
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing(
          [],
          onsCargaEnergiaVerificada,
          onsCargaEnergiaVerificada2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCargaEnergiaVerificada);
        expect(expectedResult).toContain(onsCargaEnergiaVerificada2);
      });

      it('should accept null and undefined values', () => {
        const onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada = sampleWithRequiredData;
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing([], null, onsCargaEnergiaVerificada, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCargaEnergiaVerificada);
      });

      it('should return initial array if no OnsCargaEnergiaVerificada is added', () => {
        const onsCargaEnergiaVerificadaCollection: IOnsCargaEnergiaVerificada[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCargaEnergiaVerificadaToCollectionIfMissing(onsCargaEnergiaVerificadaCollection, undefined, null);
        expect(expectedResult).toEqual(onsCargaEnergiaVerificadaCollection);
      });
    });

    describe('compareOnsCargaEnergiaVerificada', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCargaEnergiaVerificada(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 29329 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCargaEnergiaVerificada(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaVerificada(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 29329 };
        const entity2 = { id: 31492 };

        const compareResult1 = service.compareOnsCargaEnergiaVerificada(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaVerificada(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 29329 };
        const entity2 = { id: 29329 };

        const compareResult1 = service.compareOnsCargaEnergiaVerificada(entity1, entity2);
        const compareResult2 = service.compareOnsCargaEnergiaVerificada(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
