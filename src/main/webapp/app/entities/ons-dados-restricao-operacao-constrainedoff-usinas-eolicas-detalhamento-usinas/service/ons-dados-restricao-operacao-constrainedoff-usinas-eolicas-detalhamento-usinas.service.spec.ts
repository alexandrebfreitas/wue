import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.test-samples';

import {
  OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService,
  RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.service';

const requireRestSample: RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas Service', () => {
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService);
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

    it('should create a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service
        .create(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas)
        .subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service
        .update(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas)
        .subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
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

    describe('addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing', () => {
      it('should add a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas to an empty array', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          [],
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
      });

      it('should not add a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas to an array that contains it', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[] =
          [
            {
              ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas to an array that doesn't contain it", () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
      });

      it('should add only unique OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas to an array', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasArray: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection,
          ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas2: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          [],
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          [],
          null,
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
      });

      it('should return initial array if no OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas is added', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection);
      });
    });

    describe('compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 7419 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 7419 };
        const entity2 = { id: 2261 };

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 7419 };
        const entity2 = { id: 7419 };

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
