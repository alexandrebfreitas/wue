import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.test-samples';

import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService } from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.service';

const requireRestSample: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = {
  ...sampleWithRequiredData,
};

describe('OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas Service', () => {
  let service: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
    | IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService);
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

    it('should create a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
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

    describe('addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing', () => {
      it('should add a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas to an empty array', () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          [],
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
      });

      it('should not add a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas to an array that contains it', () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
          sampleWithRequiredData;
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[] =
          [
            {
              ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection,
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas to an array that doesn't contain it", () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
          sampleWithRequiredData;
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection,
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
      });

      it('should add only unique OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas to an array', () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasArray: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection,
          ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
          sampleWithRequiredData;
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas2: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          [],
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          [],
          null,
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
      });

      it('should return initial array if no OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas is added', () => {
        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing(
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection);
      });
    });

    describe('compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 3963 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 3963 };
        const entity2 = { id: 26709 };

        const compareResult1 = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 3963 };
        const entity2 = { id: 3963 };

        const compareResult1 = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
