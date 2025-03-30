import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.test-samples';

import {
  OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService,
  RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.service';

const requireRestSample: RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas Service', () => {
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService);
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

    it('should create a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
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

    describe('addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing', () => {
      it('should add a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas to an empty array', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          [],
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
      });

      it('should not add a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas to an array that contains it', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[] = [
          {
            ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas to an array that doesn't contain it", () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
      });

      it('should add only unique OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas to an array', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasArray: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection,
          ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas2: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          [],
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          [],
          null,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
      });

      it('should return initial array if no OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas is added', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection);
      });
    });

    describe('compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 11763 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 11763 };
        const entity2 = { id: 22752 };

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 11763 };
        const entity2 = { id: 11763 };

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
