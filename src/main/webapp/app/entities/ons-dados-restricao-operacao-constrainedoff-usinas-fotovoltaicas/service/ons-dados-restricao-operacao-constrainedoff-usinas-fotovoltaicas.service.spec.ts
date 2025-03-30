import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.test-samples';

import {
  OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService,
  RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';

const requireRestSample: RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas Service', () => {
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService);
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

    it('should create a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
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

    describe('addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing', () => {
      it('should add a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas to an empty array', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          [],
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
      });

      it('should not add a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas to an array that contains it', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[] =
          [
            {
              ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection,
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas to an array that doesn't contain it", () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection,
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
      });

      it('should add only unique OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas to an array', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasArray: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection,
          ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          sampleWithRequiredData;
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas2: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          [],
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          [],
          null,
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
      });

      it('should return initial array if no OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas is added', () => {
        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection);
      });
    });

    describe('compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 27295 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 27295 };
        const entity2 = { id: 130 };

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 27295 };
        const entity2 = { id: 27295 };

        const compareResult1 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity1, entity2);
        const compareResult2 = service.compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
