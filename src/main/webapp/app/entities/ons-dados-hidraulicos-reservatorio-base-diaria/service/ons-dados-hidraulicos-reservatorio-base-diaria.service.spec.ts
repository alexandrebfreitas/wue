import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsDadosHidraulicosReservatorioBaseDiaria } from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-hidraulicos-reservatorio-base-diaria.test-samples';

import { OnsDadosHidraulicosReservatorioBaseDiariaService } from './ons-dados-hidraulicos-reservatorio-base-diaria.service';

const requireRestSample: IOnsDadosHidraulicosReservatorioBaseDiaria = {
  ...sampleWithRequiredData,
};

describe('OnsDadosHidraulicosReservatorioBaseDiaria Service', () => {
  let service: OnsDadosHidraulicosReservatorioBaseDiariaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosHidraulicosReservatorioBaseDiaria | IOnsDadosHidraulicosReservatorioBaseDiaria[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosHidraulicosReservatorioBaseDiariaService);
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

    it('should create a OnsDadosHidraulicosReservatorioBaseDiaria', () => {
      const onsDadosHidraulicosReservatorioBaseDiaria = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosHidraulicosReservatorioBaseDiaria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosHidraulicosReservatorioBaseDiaria', () => {
      const onsDadosHidraulicosReservatorioBaseDiaria = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosHidraulicosReservatorioBaseDiaria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosHidraulicosReservatorioBaseDiaria', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosHidraulicosReservatorioBaseDiaria', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosHidraulicosReservatorioBaseDiaria', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosHidraulicosReservatorioBaseDiaria', () => {
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

    describe('addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing', () => {
      it('should add a OnsDadosHidraulicosReservatorioBaseDiaria to an empty array', () => {
        const onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria = sampleWithRequiredData;
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          [],
          onsDadosHidraulicosReservatorioBaseDiaria,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseDiaria);
      });

      it('should not add a OnsDadosHidraulicosReservatorioBaseDiaria to an array that contains it', () => {
        const onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria = sampleWithRequiredData;
        const onsDadosHidraulicosReservatorioBaseDiariaCollection: IOnsDadosHidraulicosReservatorioBaseDiaria[] = [
          {
            ...onsDadosHidraulicosReservatorioBaseDiaria,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseDiariaCollection,
          onsDadosHidraulicosReservatorioBaseDiaria,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosHidraulicosReservatorioBaseDiaria to an array that doesn't contain it", () => {
        const onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria = sampleWithRequiredData;
        const onsDadosHidraulicosReservatorioBaseDiariaCollection: IOnsDadosHidraulicosReservatorioBaseDiaria[] = [sampleWithPartialData];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseDiariaCollection,
          onsDadosHidraulicosReservatorioBaseDiaria,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseDiaria);
      });

      it('should add only unique OnsDadosHidraulicosReservatorioBaseDiaria to an array', () => {
        const onsDadosHidraulicosReservatorioBaseDiariaArray: IOnsDadosHidraulicosReservatorioBaseDiaria[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosHidraulicosReservatorioBaseDiariaCollection: IOnsDadosHidraulicosReservatorioBaseDiaria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseDiariaCollection,
          ...onsDadosHidraulicosReservatorioBaseDiariaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria = sampleWithRequiredData;
        const onsDadosHidraulicosReservatorioBaseDiaria2: IOnsDadosHidraulicosReservatorioBaseDiaria = sampleWithPartialData;
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          [],
          onsDadosHidraulicosReservatorioBaseDiaria,
          onsDadosHidraulicosReservatorioBaseDiaria2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseDiaria);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseDiaria2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria = sampleWithRequiredData;
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          [],
          null,
          onsDadosHidraulicosReservatorioBaseDiaria,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseDiaria);
      });

      it('should return initial array if no OnsDadosHidraulicosReservatorioBaseDiaria is added', () => {
        const onsDadosHidraulicosReservatorioBaseDiariaCollection: IOnsDadosHidraulicosReservatorioBaseDiaria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseDiariaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosHidraulicosReservatorioBaseDiariaCollection);
      });
    });

    describe('compareOnsDadosHidraulicosReservatorioBaseDiaria', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 29132 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 29132 };
        const entity2 = { id: 7299 };

        const compareResult1 = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 29132 };
        const entity2 = { id: 29132 };

        const compareResult1 = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHidraulicosReservatorioBaseDiaria(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
