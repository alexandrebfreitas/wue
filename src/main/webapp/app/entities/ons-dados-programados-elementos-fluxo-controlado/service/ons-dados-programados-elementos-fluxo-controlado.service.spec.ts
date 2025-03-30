import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosProgramadosElementosFluxoControlado } from '../ons-dados-programados-elementos-fluxo-controlado.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-programados-elementos-fluxo-controlado.test-samples';

import {
  OnsDadosProgramadosElementosFluxoControladoService,
  RestOnsDadosProgramadosElementosFluxoControlado,
} from './ons-dados-programados-elementos-fluxo-controlado.service';

const requireRestSample: RestOnsDadosProgramadosElementosFluxoControlado = {
  ...sampleWithRequiredData,
  dinProgramacaodia: sampleWithRequiredData.dinProgramacaodia?.format(DATE_FORMAT),
};

describe('OnsDadosProgramadosElementosFluxoControlado Service', () => {
  let service: OnsDadosProgramadosElementosFluxoControladoService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosProgramadosElementosFluxoControlado | IOnsDadosProgramadosElementosFluxoControlado[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosProgramadosElementosFluxoControladoService);
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

    it('should create a OnsDadosProgramadosElementosFluxoControlado', () => {
      const onsDadosProgramadosElementosFluxoControlado = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosProgramadosElementosFluxoControlado).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosProgramadosElementosFluxoControlado', () => {
      const onsDadosProgramadosElementosFluxoControlado = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosProgramadosElementosFluxoControlado).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosProgramadosElementosFluxoControlado', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosProgramadosElementosFluxoControlado', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosProgramadosElementosFluxoControlado', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosProgramadosElementosFluxoControlado', () => {
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

    describe('addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing', () => {
      it('should add a OnsDadosProgramadosElementosFluxoControlado to an empty array', () => {
        const onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado = sampleWithRequiredData;
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          [],
          onsDadosProgramadosElementosFluxoControlado,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosProgramadosElementosFluxoControlado);
      });

      it('should not add a OnsDadosProgramadosElementosFluxoControlado to an array that contains it', () => {
        const onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado = sampleWithRequiredData;
        const onsDadosProgramadosElementosFluxoControladoCollection: IOnsDadosProgramadosElementosFluxoControlado[] = [
          {
            ...onsDadosProgramadosElementosFluxoControlado,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          onsDadosProgramadosElementosFluxoControladoCollection,
          onsDadosProgramadosElementosFluxoControlado,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosProgramadosElementosFluxoControlado to an array that doesn't contain it", () => {
        const onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado = sampleWithRequiredData;
        const onsDadosProgramadosElementosFluxoControladoCollection: IOnsDadosProgramadosElementosFluxoControlado[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          onsDadosProgramadosElementosFluxoControladoCollection,
          onsDadosProgramadosElementosFluxoControlado,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosProgramadosElementosFluxoControlado);
      });

      it('should add only unique OnsDadosProgramadosElementosFluxoControlado to an array', () => {
        const onsDadosProgramadosElementosFluxoControladoArray: IOnsDadosProgramadosElementosFluxoControlado[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosProgramadosElementosFluxoControladoCollection: IOnsDadosProgramadosElementosFluxoControlado[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          onsDadosProgramadosElementosFluxoControladoCollection,
          ...onsDadosProgramadosElementosFluxoControladoArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado = sampleWithRequiredData;
        const onsDadosProgramadosElementosFluxoControlado2: IOnsDadosProgramadosElementosFluxoControlado = sampleWithPartialData;
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          [],
          onsDadosProgramadosElementosFluxoControlado,
          onsDadosProgramadosElementosFluxoControlado2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosProgramadosElementosFluxoControlado);
        expect(expectedResult).toContain(onsDadosProgramadosElementosFluxoControlado2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado = sampleWithRequiredData;
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          [],
          null,
          onsDadosProgramadosElementosFluxoControlado,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosProgramadosElementosFluxoControlado);
      });

      it('should return initial array if no OnsDadosProgramadosElementosFluxoControlado is added', () => {
        const onsDadosProgramadosElementosFluxoControladoCollection: IOnsDadosProgramadosElementosFluxoControlado[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing(
          onsDadosProgramadosElementosFluxoControladoCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosProgramadosElementosFluxoControladoCollection);
      });
    });

    describe('compareOnsDadosProgramadosElementosFluxoControlado', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosProgramadosElementosFluxoControlado(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 17757 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosProgramadosElementosFluxoControlado(entity1, entity2);
        const compareResult2 = service.compareOnsDadosProgramadosElementosFluxoControlado(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 17757 };
        const entity2 = { id: 10318 };

        const compareResult1 = service.compareOnsDadosProgramadosElementosFluxoControlado(entity1, entity2);
        const compareResult2 = service.compareOnsDadosProgramadosElementosFluxoControlado(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 17757 };
        const entity2 = { id: 17757 };

        const compareResult1 = service.compareOnsDadosProgramadosElementosFluxoControlado(entity1, entity2);
        const compareResult2 = service.compareOnsDadosProgramadosElementosFluxoControlado(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
