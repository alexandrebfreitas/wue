import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.test-samples';

import {
  OnsDadosRelacionamentoEntreConjuntosEUsinasService,
  RestOnsDadosRelacionamentoEntreConjuntosEUsinas,
} from './ons-dados-relacionamento-entre-conjuntos-e-usinas.service';

const requireRestSample: RestOnsDadosRelacionamentoEntreConjuntosEUsinas = {
  ...sampleWithRequiredData,
  datIniciorelacionamento: sampleWithRequiredData.datIniciorelacionamento?.format(DATE_FORMAT),
  datFimrelacionamento: sampleWithRequiredData.datFimrelacionamento?.format(DATE_FORMAT),
};

describe('OnsDadosRelacionamentoEntreConjuntosEUsinas Service', () => {
  let service: OnsDadosRelacionamentoEntreConjuntosEUsinasService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosRelacionamentoEntreConjuntosEUsinas | IOnsDadosRelacionamentoEntreConjuntosEUsinas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService);
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

    it('should create a OnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      const onsDadosRelacionamentoEntreConjuntosEUsinas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosRelacionamentoEntreConjuntosEUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      const onsDadosRelacionamentoEntreConjuntosEUsinas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosRelacionamentoEntreConjuntosEUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
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

    describe('addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing', () => {
      it('should add a OnsDadosRelacionamentoEntreConjuntosEUsinas to an empty array', () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas = sampleWithRequiredData;
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          [],
          onsDadosRelacionamentoEntreConjuntosEUsinas,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreConjuntosEUsinas);
      });

      it('should not add a OnsDadosRelacionamentoEntreConjuntosEUsinas to an array that contains it', () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas = sampleWithRequiredData;
        const onsDadosRelacionamentoEntreConjuntosEUsinasCollection: IOnsDadosRelacionamentoEntreConjuntosEUsinas[] = [
          {
            ...onsDadosRelacionamentoEntreConjuntosEUsinas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreConjuntosEUsinasCollection,
          onsDadosRelacionamentoEntreConjuntosEUsinas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosRelacionamentoEntreConjuntosEUsinas to an array that doesn't contain it", () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas = sampleWithRequiredData;
        const onsDadosRelacionamentoEntreConjuntosEUsinasCollection: IOnsDadosRelacionamentoEntreConjuntosEUsinas[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreConjuntosEUsinasCollection,
          onsDadosRelacionamentoEntreConjuntosEUsinas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreConjuntosEUsinas);
      });

      it('should add only unique OnsDadosRelacionamentoEntreConjuntosEUsinas to an array', () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinasArray: IOnsDadosRelacionamentoEntreConjuntosEUsinas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosRelacionamentoEntreConjuntosEUsinasCollection: IOnsDadosRelacionamentoEntreConjuntosEUsinas[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreConjuntosEUsinasCollection,
          ...onsDadosRelacionamentoEntreConjuntosEUsinasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas = sampleWithRequiredData;
        const onsDadosRelacionamentoEntreConjuntosEUsinas2: IOnsDadosRelacionamentoEntreConjuntosEUsinas = sampleWithPartialData;
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          [],
          onsDadosRelacionamentoEntreConjuntosEUsinas,
          onsDadosRelacionamentoEntreConjuntosEUsinas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreConjuntosEUsinas);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreConjuntosEUsinas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas = sampleWithRequiredData;
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          [],
          null,
          onsDadosRelacionamentoEntreConjuntosEUsinas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreConjuntosEUsinas);
      });

      it('should return initial array if no OnsDadosRelacionamentoEntreConjuntosEUsinas is added', () => {
        const onsDadosRelacionamentoEntreConjuntosEUsinasCollection: IOnsDadosRelacionamentoEntreConjuntosEUsinas[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreConjuntosEUsinasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosRelacionamentoEntreConjuntosEUsinasCollection);
      });
    });

    describe('compareOnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 30752 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 30752 };
        const entity2 = { id: 1430 };

        const compareResult1 = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 30752 };
        const entity2 = { id: 30752 };

        const compareResult1 = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRelacionamentoEntreConjuntosEUsinas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
