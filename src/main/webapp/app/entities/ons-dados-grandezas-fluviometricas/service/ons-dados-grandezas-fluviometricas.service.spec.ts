import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-grandezas-fluviometricas.test-samples';

import { OnsDadosGrandezasFluviometricasService, RestOnsDadosGrandezasFluviometricas } from './ons-dados-grandezas-fluviometricas.service';

const requireRestSample: RestOnsDadosGrandezasFluviometricas = {
  ...sampleWithRequiredData,
  dinMedicao: sampleWithRequiredData.dinMedicao?.format(DATE_FORMAT),
};

describe('OnsDadosGrandezasFluviometricas Service', () => {
  let service: OnsDadosGrandezasFluviometricasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosGrandezasFluviometricas | IOnsDadosGrandezasFluviometricas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosGrandezasFluviometricasService);
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

    it('should create a OnsDadosGrandezasFluviometricas', () => {
      const onsDadosGrandezasFluviometricas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosGrandezasFluviometricas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosGrandezasFluviometricas', () => {
      const onsDadosGrandezasFluviometricas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosGrandezasFluviometricas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosGrandezasFluviometricas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosGrandezasFluviometricas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosGrandezasFluviometricas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosGrandezasFluviometricas', () => {
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

    describe('addOnsDadosGrandezasFluviometricasToCollectionIfMissing', () => {
      it('should add a OnsDadosGrandezasFluviometricas to an empty array', () => {
        const onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas = sampleWithRequiredData;
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing([], onsDadosGrandezasFluviometricas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosGrandezasFluviometricas);
      });

      it('should not add a OnsDadosGrandezasFluviometricas to an array that contains it', () => {
        const onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas = sampleWithRequiredData;
        const onsDadosGrandezasFluviometricasCollection: IOnsDadosGrandezasFluviometricas[] = [
          {
            ...onsDadosGrandezasFluviometricas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing(
          onsDadosGrandezasFluviometricasCollection,
          onsDadosGrandezasFluviometricas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosGrandezasFluviometricas to an array that doesn't contain it", () => {
        const onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas = sampleWithRequiredData;
        const onsDadosGrandezasFluviometricasCollection: IOnsDadosGrandezasFluviometricas[] = [sampleWithPartialData];
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing(
          onsDadosGrandezasFluviometricasCollection,
          onsDadosGrandezasFluviometricas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosGrandezasFluviometricas);
      });

      it('should add only unique OnsDadosGrandezasFluviometricas to an array', () => {
        const onsDadosGrandezasFluviometricasArray: IOnsDadosGrandezasFluviometricas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosGrandezasFluviometricasCollection: IOnsDadosGrandezasFluviometricas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing(
          onsDadosGrandezasFluviometricasCollection,
          ...onsDadosGrandezasFluviometricasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas = sampleWithRequiredData;
        const onsDadosGrandezasFluviometricas2: IOnsDadosGrandezasFluviometricas = sampleWithPartialData;
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing(
          [],
          onsDadosGrandezasFluviometricas,
          onsDadosGrandezasFluviometricas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosGrandezasFluviometricas);
        expect(expectedResult).toContain(onsDadosGrandezasFluviometricas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas = sampleWithRequiredData;
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing(
          [],
          null,
          onsDadosGrandezasFluviometricas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosGrandezasFluviometricas);
      });

      it('should return initial array if no OnsDadosGrandezasFluviometricas is added', () => {
        const onsDadosGrandezasFluviometricasCollection: IOnsDadosGrandezasFluviometricas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosGrandezasFluviometricasToCollectionIfMissing(
          onsDadosGrandezasFluviometricasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosGrandezasFluviometricasCollection);
      });
    });

    describe('compareOnsDadosGrandezasFluviometricas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosGrandezasFluviometricas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 29394 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosGrandezasFluviometricas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosGrandezasFluviometricas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 29394 };
        const entity2 = { id: 18806 };

        const compareResult1 = service.compareOnsDadosGrandezasFluviometricas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosGrandezasFluviometricas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 29394 };
        const entity2 = { id: 29394 };

        const compareResult1 = service.compareOnsDadosGrandezasFluviometricas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosGrandezasFluviometricas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
