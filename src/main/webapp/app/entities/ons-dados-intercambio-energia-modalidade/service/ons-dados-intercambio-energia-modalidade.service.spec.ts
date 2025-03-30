import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-intercambio-energia-modalidade.test-samples';

import {
  OnsDadosIntercambioEnergiaModalidadeService,
  RestOnsDadosIntercambioEnergiaModalidade,
} from './ons-dados-intercambio-energia-modalidade.service';

const requireRestSample: RestOnsDadosIntercambioEnergiaModalidade = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsDadosIntercambioEnergiaModalidade Service', () => {
  let service: OnsDadosIntercambioEnergiaModalidadeService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosIntercambioEnergiaModalidade | IOnsDadosIntercambioEnergiaModalidade[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosIntercambioEnergiaModalidadeService);
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

    it('should create a OnsDadosIntercambioEnergiaModalidade', () => {
      const onsDadosIntercambioEnergiaModalidade = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosIntercambioEnergiaModalidade).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosIntercambioEnergiaModalidade', () => {
      const onsDadosIntercambioEnergiaModalidade = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosIntercambioEnergiaModalidade).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosIntercambioEnergiaModalidade', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosIntercambioEnergiaModalidade', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosIntercambioEnergiaModalidade', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosIntercambioEnergiaModalidade', () => {
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

    describe('addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing', () => {
      it('should add a OnsDadosIntercambioEnergiaModalidade to an empty array', () => {
        const onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade = sampleWithRequiredData;
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing([], onsDadosIntercambioEnergiaModalidade);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosIntercambioEnergiaModalidade);
      });

      it('should not add a OnsDadosIntercambioEnergiaModalidade to an array that contains it', () => {
        const onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade = sampleWithRequiredData;
        const onsDadosIntercambioEnergiaModalidadeCollection: IOnsDadosIntercambioEnergiaModalidade[] = [
          {
            ...onsDadosIntercambioEnergiaModalidade,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing(
          onsDadosIntercambioEnergiaModalidadeCollection,
          onsDadosIntercambioEnergiaModalidade,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosIntercambioEnergiaModalidade to an array that doesn't contain it", () => {
        const onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade = sampleWithRequiredData;
        const onsDadosIntercambioEnergiaModalidadeCollection: IOnsDadosIntercambioEnergiaModalidade[] = [sampleWithPartialData];
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing(
          onsDadosIntercambioEnergiaModalidadeCollection,
          onsDadosIntercambioEnergiaModalidade,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosIntercambioEnergiaModalidade);
      });

      it('should add only unique OnsDadosIntercambioEnergiaModalidade to an array', () => {
        const onsDadosIntercambioEnergiaModalidadeArray: IOnsDadosIntercambioEnergiaModalidade[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosIntercambioEnergiaModalidadeCollection: IOnsDadosIntercambioEnergiaModalidade[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing(
          onsDadosIntercambioEnergiaModalidadeCollection,
          ...onsDadosIntercambioEnergiaModalidadeArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade = sampleWithRequiredData;
        const onsDadosIntercambioEnergiaModalidade2: IOnsDadosIntercambioEnergiaModalidade = sampleWithPartialData;
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing(
          [],
          onsDadosIntercambioEnergiaModalidade,
          onsDadosIntercambioEnergiaModalidade2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosIntercambioEnergiaModalidade);
        expect(expectedResult).toContain(onsDadosIntercambioEnergiaModalidade2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade = sampleWithRequiredData;
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing(
          [],
          null,
          onsDadosIntercambioEnergiaModalidade,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosIntercambioEnergiaModalidade);
      });

      it('should return initial array if no OnsDadosIntercambioEnergiaModalidade is added', () => {
        const onsDadosIntercambioEnergiaModalidadeCollection: IOnsDadosIntercambioEnergiaModalidade[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing(
          onsDadosIntercambioEnergiaModalidadeCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosIntercambioEnergiaModalidadeCollection);
      });
    });

    describe('compareOnsDadosIntercambioEnergiaModalidade', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosIntercambioEnergiaModalidade(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 15687 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosIntercambioEnergiaModalidade(entity1, entity2);
        const compareResult2 = service.compareOnsDadosIntercambioEnergiaModalidade(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 15687 };
        const entity2 = { id: 24650 };

        const compareResult1 = service.compareOnsDadosIntercambioEnergiaModalidade(entity1, entity2);
        const compareResult2 = service.compareOnsDadosIntercambioEnergiaModalidade(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 15687 };
        const entity2 = { id: 15687 };

        const compareResult1 = service.compareOnsDadosIntercambioEnergiaModalidade(entity1, entity2);
        const compareResult2 = service.compareOnsDadosIntercambioEnergiaModalidade(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
