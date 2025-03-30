import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-cvu-usina-termicas.test-samples';

import { OnsCvuUsinaTermicasService, RestOnsCvuUsinaTermicas } from './ons-cvu-usina-termicas.service';

const requireRestSample: RestOnsCvuUsinaTermicas = {
  ...sampleWithRequiredData,
  datIniciosemana: sampleWithRequiredData.datIniciosemana?.format(DATE_FORMAT),
  datFimsemana: sampleWithRequiredData.datFimsemana?.format(DATE_FORMAT),
};

describe('OnsCvuUsinaTermicas Service', () => {
  let service: OnsCvuUsinaTermicasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCvuUsinaTermicas | IOnsCvuUsinaTermicas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCvuUsinaTermicasService);
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

    it('should create a OnsCvuUsinaTermicas', () => {
      const onsCvuUsinaTermicas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCvuUsinaTermicas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCvuUsinaTermicas', () => {
      const onsCvuUsinaTermicas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCvuUsinaTermicas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCvuUsinaTermicas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCvuUsinaTermicas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCvuUsinaTermicas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCvuUsinaTermicas', () => {
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

    describe('addOnsCvuUsinaTermicasToCollectionIfMissing', () => {
      it('should add a OnsCvuUsinaTermicas to an empty array', () => {
        const onsCvuUsinaTermicas: IOnsCvuUsinaTermicas = sampleWithRequiredData;
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing([], onsCvuUsinaTermicas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCvuUsinaTermicas);
      });

      it('should not add a OnsCvuUsinaTermicas to an array that contains it', () => {
        const onsCvuUsinaTermicas: IOnsCvuUsinaTermicas = sampleWithRequiredData;
        const onsCvuUsinaTermicasCollection: IOnsCvuUsinaTermicas[] = [
          {
            ...onsCvuUsinaTermicas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing(onsCvuUsinaTermicasCollection, onsCvuUsinaTermicas);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCvuUsinaTermicas to an array that doesn't contain it", () => {
        const onsCvuUsinaTermicas: IOnsCvuUsinaTermicas = sampleWithRequiredData;
        const onsCvuUsinaTermicasCollection: IOnsCvuUsinaTermicas[] = [sampleWithPartialData];
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing(onsCvuUsinaTermicasCollection, onsCvuUsinaTermicas);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCvuUsinaTermicas);
      });

      it('should add only unique OnsCvuUsinaTermicas to an array', () => {
        const onsCvuUsinaTermicasArray: IOnsCvuUsinaTermicas[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsCvuUsinaTermicasCollection: IOnsCvuUsinaTermicas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing(onsCvuUsinaTermicasCollection, ...onsCvuUsinaTermicasArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCvuUsinaTermicas: IOnsCvuUsinaTermicas = sampleWithRequiredData;
        const onsCvuUsinaTermicas2: IOnsCvuUsinaTermicas = sampleWithPartialData;
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing([], onsCvuUsinaTermicas, onsCvuUsinaTermicas2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCvuUsinaTermicas);
        expect(expectedResult).toContain(onsCvuUsinaTermicas2);
      });

      it('should accept null and undefined values', () => {
        const onsCvuUsinaTermicas: IOnsCvuUsinaTermicas = sampleWithRequiredData;
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing([], null, onsCvuUsinaTermicas, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCvuUsinaTermicas);
      });

      it('should return initial array if no OnsCvuUsinaTermicas is added', () => {
        const onsCvuUsinaTermicasCollection: IOnsCvuUsinaTermicas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCvuUsinaTermicasToCollectionIfMissing(onsCvuUsinaTermicasCollection, undefined, null);
        expect(expectedResult).toEqual(onsCvuUsinaTermicasCollection);
      });
    });

    describe('compareOnsCvuUsinaTermicas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCvuUsinaTermicas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 30136 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCvuUsinaTermicas(entity1, entity2);
        const compareResult2 = service.compareOnsCvuUsinaTermicas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 30136 };
        const entity2 = { id: 16744 };

        const compareResult1 = service.compareOnsCvuUsinaTermicas(entity1, entity2);
        const compareResult2 = service.compareOnsCvuUsinaTermicas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 30136 };
        const entity2 = { id: 30136 };

        const compareResult1 = service.compareOnsCvuUsinaTermicas(entity1, entity2);
        const compareResult2 = service.compareOnsCvuUsinaTermicas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
