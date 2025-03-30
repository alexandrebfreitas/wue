import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.test-samples';

import {
  OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService,
  RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
} from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.service';

const requireRestSample: RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = {
  ...sampleWithRequiredData,
  dinReferencia: sampleWithRequiredData.dinReferencia?.format(DATE_FORMAT),
};

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores Service', () => {
  let service: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
    | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService);
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

    it('should create a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service
        .create(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores)
        .subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service
        .update(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores)
        .subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
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

    describe('addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing', () => {
      it('should add a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores to an empty array', () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          [],
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
      });

      it('should not add a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores to an array that contains it', () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          sampleWithRequiredData;
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[] =
          [
            {
              ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection,
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores to an array that doesn't contain it", () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          sampleWithRequiredData;
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection,
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
      });

      it('should add only unique OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores to an array', () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresArray: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection,
          ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          sampleWithRequiredData;
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores2: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          [],
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
        expect(expectedResult).toContain(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          [],
          null,
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
      });

      it('should return initial array if no OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores is added', () => {
        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection);
      });
    });

    describe('compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity1,
          entity2,
        );

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 28122 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity1,
          entity2,
        );
        const compareResult2 = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity2,
          entity1,
        );

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 28122 };
        const entity2 = { id: 1611 };

        const compareResult1 = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity1,
          entity2,
        );
        const compareResult2 = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity2,
          entity1,
        );

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 28122 };
        const entity2 = { id: 28122 };

        const compareResult1 = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity1,
          entity2,
        );
        const compareResult2 = service.compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
          entity2,
          entity1,
        );

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
