import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.test-samples';

import {
  OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService,
  RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
} from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';

const requireRestSample: RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = {
  ...sampleWithRequiredData,
  dinReferencia: sampleWithRequiredData.dinReferencia?.format(DATE_FORMAT),
};

describe('OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
    | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService);
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

    it('should create a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
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

    describe('addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing', () => {
      it('should add a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes to an empty array', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          [],
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
      });

      it('should not add a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes to an array that contains it', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] =
          [
            {
              ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection,
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes to an array that doesn't contain it", () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection,
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
      });

      it('should add only unique OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes to an array', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesArray: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection,
          ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes2: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          sampleWithPartialData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          [],
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes2);
      });

      it('should accept null and undefined values', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          [],
          null,
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
      });

      it('should return initial array if no OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes is added', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection);
      });
    });

    describe('compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 25854 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 25854 };
        const entity2 = { id: 10204 };

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 25854 };
        const entity2 = { id: 25854 };

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
