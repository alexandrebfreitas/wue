import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-hidraulicos-reservatorio-base-horaria.test-samples';

import { OnsDadosHidraulicosReservatorioBaseHorariaService } from './ons-dados-hidraulicos-reservatorio-base-horaria.service';

const requireRestSample: IOnsDadosHidraulicosReservatorioBaseHoraria = {
  ...sampleWithRequiredData,
};

describe('OnsDadosHidraulicosReservatorioBaseHoraria Service', () => {
  let service: OnsDadosHidraulicosReservatorioBaseHorariaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDadosHidraulicosReservatorioBaseHoraria | IOnsDadosHidraulicosReservatorioBaseHoraria[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosHidraulicosReservatorioBaseHorariaService);
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

    it('should create a OnsDadosHidraulicosReservatorioBaseHoraria', () => {
      const onsDadosHidraulicosReservatorioBaseHoraria = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosHidraulicosReservatorioBaseHoraria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosHidraulicosReservatorioBaseHoraria', () => {
      const onsDadosHidraulicosReservatorioBaseHoraria = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosHidraulicosReservatorioBaseHoraria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosHidraulicosReservatorioBaseHoraria', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosHidraulicosReservatorioBaseHoraria', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosHidraulicosReservatorioBaseHoraria', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosHidraulicosReservatorioBaseHoraria', () => {
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

    describe('addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing', () => {
      it('should add a OnsDadosHidraulicosReservatorioBaseHoraria to an empty array', () => {
        const onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria = sampleWithRequiredData;
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          [],
          onsDadosHidraulicosReservatorioBaseHoraria,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseHoraria);
      });

      it('should not add a OnsDadosHidraulicosReservatorioBaseHoraria to an array that contains it', () => {
        const onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria = sampleWithRequiredData;
        const onsDadosHidraulicosReservatorioBaseHorariaCollection: IOnsDadosHidraulicosReservatorioBaseHoraria[] = [
          {
            ...onsDadosHidraulicosReservatorioBaseHoraria,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseHorariaCollection,
          onsDadosHidraulicosReservatorioBaseHoraria,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosHidraulicosReservatorioBaseHoraria to an array that doesn't contain it", () => {
        const onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria = sampleWithRequiredData;
        const onsDadosHidraulicosReservatorioBaseHorariaCollection: IOnsDadosHidraulicosReservatorioBaseHoraria[] = [sampleWithPartialData];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseHorariaCollection,
          onsDadosHidraulicosReservatorioBaseHoraria,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseHoraria);
      });

      it('should add only unique OnsDadosHidraulicosReservatorioBaseHoraria to an array', () => {
        const onsDadosHidraulicosReservatorioBaseHorariaArray: IOnsDadosHidraulicosReservatorioBaseHoraria[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosHidraulicosReservatorioBaseHorariaCollection: IOnsDadosHidraulicosReservatorioBaseHoraria[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseHorariaCollection,
          ...onsDadosHidraulicosReservatorioBaseHorariaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria = sampleWithRequiredData;
        const onsDadosHidraulicosReservatorioBaseHoraria2: IOnsDadosHidraulicosReservatorioBaseHoraria = sampleWithPartialData;
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          [],
          onsDadosHidraulicosReservatorioBaseHoraria,
          onsDadosHidraulicosReservatorioBaseHoraria2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseHoraria);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseHoraria2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria = sampleWithRequiredData;
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          [],
          null,
          onsDadosHidraulicosReservatorioBaseHoraria,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosHidraulicosReservatorioBaseHoraria);
      });

      it('should return initial array if no OnsDadosHidraulicosReservatorioBaseHoraria is added', () => {
        const onsDadosHidraulicosReservatorioBaseHorariaCollection: IOnsDadosHidraulicosReservatorioBaseHoraria[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing(
          onsDadosHidraulicosReservatorioBaseHorariaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosHidraulicosReservatorioBaseHorariaCollection);
      });
    });

    describe('compareOnsDadosHidraulicosReservatorioBaseHoraria', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 14970 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 14970 };
        const entity2 = { id: 3484 };

        const compareResult1 = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 14970 };
        const entity2 = { id: 14970 };

        const compareResult1 = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHidraulicosReservatorioBaseHoraria(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
