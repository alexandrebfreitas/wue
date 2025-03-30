import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.test-samples';

import {
  OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService,
  RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
} from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';

const requireRestSample: RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = {
  ...sampleWithRequiredData,
  dinReferencia: sampleWithRequiredData.dinReferencia?.format(DATE_FORMAT),
};

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
    | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService);
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

    it('should create a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service
        .create(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes)
        .subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service
        .update(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes)
        .subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
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

    describe('addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing', () => {
      it('should add a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes to an empty array', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          [],
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
      });

      it('should not add a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes to an array that contains it', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[] =
          [
            {
              ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection,
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes to an array that doesn't contain it", () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection,
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
      });

      it('should add only unique OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes to an array', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesArray: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection,
          ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes2: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          sampleWithPartialData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          [],
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes2);
      });

      it('should accept null and undefined values', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          [],
          null,
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
      });

      it('should return initial array if no OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes is added', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection);
      });
    });

    describe('compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 511 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 511 };
        const entity2 = { id: 30044 };

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 511 };
        const entity2 = { id: 511 };

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
