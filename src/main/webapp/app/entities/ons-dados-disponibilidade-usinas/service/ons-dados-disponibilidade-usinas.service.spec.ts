import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-disponibilidade-usinas.test-samples';

import { OnsDadosDisponibilidadeUsinasService, RestOnsDadosDisponibilidadeUsinas } from './ons-dados-disponibilidade-usinas.service';

const requireRestSample: RestOnsDadosDisponibilidadeUsinas = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsDadosDisponibilidadeUsinas Service', () => {
  let service: OnsDadosDisponibilidadeUsinasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosDisponibilidadeUsinas | IOnsDadosDisponibilidadeUsinas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosDisponibilidadeUsinasService);
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

    it('should create a OnsDadosDisponibilidadeUsinas', () => {
      const onsDadosDisponibilidadeUsinas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosDisponibilidadeUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosDisponibilidadeUsinas', () => {
      const onsDadosDisponibilidadeUsinas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosDisponibilidadeUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosDisponibilidadeUsinas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosDisponibilidadeUsinas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosDisponibilidadeUsinas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosDisponibilidadeUsinas', () => {
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

    describe('addOnsDadosDisponibilidadeUsinasToCollectionIfMissing', () => {
      it('should add a OnsDadosDisponibilidadeUsinas to an empty array', () => {
        const onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas = sampleWithRequiredData;
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing([], onsDadosDisponibilidadeUsinas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosDisponibilidadeUsinas);
      });

      it('should not add a OnsDadosDisponibilidadeUsinas to an array that contains it', () => {
        const onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas = sampleWithRequiredData;
        const onsDadosDisponibilidadeUsinasCollection: IOnsDadosDisponibilidadeUsinas[] = [
          {
            ...onsDadosDisponibilidadeUsinas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing(
          onsDadosDisponibilidadeUsinasCollection,
          onsDadosDisponibilidadeUsinas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosDisponibilidadeUsinas to an array that doesn't contain it", () => {
        const onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas = sampleWithRequiredData;
        const onsDadosDisponibilidadeUsinasCollection: IOnsDadosDisponibilidadeUsinas[] = [sampleWithPartialData];
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing(
          onsDadosDisponibilidadeUsinasCollection,
          onsDadosDisponibilidadeUsinas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosDisponibilidadeUsinas);
      });

      it('should add only unique OnsDadosDisponibilidadeUsinas to an array', () => {
        const onsDadosDisponibilidadeUsinasArray: IOnsDadosDisponibilidadeUsinas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosDisponibilidadeUsinasCollection: IOnsDadosDisponibilidadeUsinas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing(
          onsDadosDisponibilidadeUsinasCollection,
          ...onsDadosDisponibilidadeUsinasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas = sampleWithRequiredData;
        const onsDadosDisponibilidadeUsinas2: IOnsDadosDisponibilidadeUsinas = sampleWithPartialData;
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing(
          [],
          onsDadosDisponibilidadeUsinas,
          onsDadosDisponibilidadeUsinas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosDisponibilidadeUsinas);
        expect(expectedResult).toContain(onsDadosDisponibilidadeUsinas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas = sampleWithRequiredData;
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing([], null, onsDadosDisponibilidadeUsinas, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosDisponibilidadeUsinas);
      });

      it('should return initial array if no OnsDadosDisponibilidadeUsinas is added', () => {
        const onsDadosDisponibilidadeUsinasCollection: IOnsDadosDisponibilidadeUsinas[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosDisponibilidadeUsinasToCollectionIfMissing(
          onsDadosDisponibilidadeUsinasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosDisponibilidadeUsinasCollection);
      });
    });

    describe('compareOnsDadosDisponibilidadeUsinas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosDisponibilidadeUsinas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 14685 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosDisponibilidadeUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosDisponibilidadeUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 14685 };
        const entity2 = { id: 10417 };

        const compareResult1 = service.compareOnsDadosDisponibilidadeUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosDisponibilidadeUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 14685 };
        const entity2 = { id: 14685 };

        const compareResult1 = service.compareOnsDadosDisponibilidadeUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosDisponibilidadeUsinas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
