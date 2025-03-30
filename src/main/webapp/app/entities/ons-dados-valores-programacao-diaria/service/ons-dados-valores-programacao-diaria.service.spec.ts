import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-valores-programacao-diaria.test-samples';

import {
  OnsDadosValoresProgramacaoDiariaService,
  RestOnsDadosValoresProgramacaoDiaria,
} from './ons-dados-valores-programacao-diaria.service';

const requireRestSample: RestOnsDadosValoresProgramacaoDiaria = {
  ...sampleWithRequiredData,
  dinProgramacaodia: sampleWithRequiredData.dinProgramacaodia?.format(DATE_FORMAT),
};

describe('OnsDadosValoresProgramacaoDiaria Service', () => {
  let service: OnsDadosValoresProgramacaoDiariaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosValoresProgramacaoDiaria | IOnsDadosValoresProgramacaoDiaria[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosValoresProgramacaoDiariaService);
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

    it('should create a OnsDadosValoresProgramacaoDiaria', () => {
      const onsDadosValoresProgramacaoDiaria = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosValoresProgramacaoDiaria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosValoresProgramacaoDiaria', () => {
      const onsDadosValoresProgramacaoDiaria = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosValoresProgramacaoDiaria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosValoresProgramacaoDiaria', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosValoresProgramacaoDiaria', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosValoresProgramacaoDiaria', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosValoresProgramacaoDiaria', () => {
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

    describe('addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing', () => {
      it('should add a OnsDadosValoresProgramacaoDiaria to an empty array', () => {
        const onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria = sampleWithRequiredData;
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing([], onsDadosValoresProgramacaoDiaria);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosValoresProgramacaoDiaria);
      });

      it('should not add a OnsDadosValoresProgramacaoDiaria to an array that contains it', () => {
        const onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria = sampleWithRequiredData;
        const onsDadosValoresProgramacaoDiariaCollection: IOnsDadosValoresProgramacaoDiaria[] = [
          {
            ...onsDadosValoresProgramacaoDiaria,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing(
          onsDadosValoresProgramacaoDiariaCollection,
          onsDadosValoresProgramacaoDiaria,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosValoresProgramacaoDiaria to an array that doesn't contain it", () => {
        const onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria = sampleWithRequiredData;
        const onsDadosValoresProgramacaoDiariaCollection: IOnsDadosValoresProgramacaoDiaria[] = [sampleWithPartialData];
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing(
          onsDadosValoresProgramacaoDiariaCollection,
          onsDadosValoresProgramacaoDiaria,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosValoresProgramacaoDiaria);
      });

      it('should add only unique OnsDadosValoresProgramacaoDiaria to an array', () => {
        const onsDadosValoresProgramacaoDiariaArray: IOnsDadosValoresProgramacaoDiaria[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosValoresProgramacaoDiariaCollection: IOnsDadosValoresProgramacaoDiaria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing(
          onsDadosValoresProgramacaoDiariaCollection,
          ...onsDadosValoresProgramacaoDiariaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria = sampleWithRequiredData;
        const onsDadosValoresProgramacaoDiaria2: IOnsDadosValoresProgramacaoDiaria = sampleWithPartialData;
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing(
          [],
          onsDadosValoresProgramacaoDiaria,
          onsDadosValoresProgramacaoDiaria2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosValoresProgramacaoDiaria);
        expect(expectedResult).toContain(onsDadosValoresProgramacaoDiaria2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria = sampleWithRequiredData;
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing(
          [],
          null,
          onsDadosValoresProgramacaoDiaria,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosValoresProgramacaoDiaria);
      });

      it('should return initial array if no OnsDadosValoresProgramacaoDiaria is added', () => {
        const onsDadosValoresProgramacaoDiariaCollection: IOnsDadosValoresProgramacaoDiaria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing(
          onsDadosValoresProgramacaoDiariaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosValoresProgramacaoDiariaCollection);
      });
    });

    describe('compareOnsDadosValoresProgramacaoDiaria', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosValoresProgramacaoDiaria(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 9203 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosValoresProgramacaoDiaria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosValoresProgramacaoDiaria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 9203 };
        const entity2 = { id: 2639 };

        const compareResult1 = service.compareOnsDadosValoresProgramacaoDiaria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosValoresProgramacaoDiaria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 9203 };
        const entity2 = { id: 9203 };

        const compareResult1 = service.compareOnsDadosValoresProgramacaoDiaria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosValoresProgramacaoDiaria(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
