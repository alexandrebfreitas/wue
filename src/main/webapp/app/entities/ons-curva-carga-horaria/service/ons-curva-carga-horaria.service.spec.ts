import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-curva-carga-horaria.test-samples';

import { OnsCurvaCargaHorariaService, RestOnsCurvaCargaHoraria } from './ons-curva-carga-horaria.service';

const requireRestSample: RestOnsCurvaCargaHoraria = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsCurvaCargaHoraria Service', () => {
  let service: OnsCurvaCargaHorariaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCurvaCargaHoraria | IOnsCurvaCargaHoraria[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCurvaCargaHorariaService);
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

    it('should create a OnsCurvaCargaHoraria', () => {
      const onsCurvaCargaHoraria = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCurvaCargaHoraria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCurvaCargaHoraria', () => {
      const onsCurvaCargaHoraria = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCurvaCargaHoraria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCurvaCargaHoraria', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCurvaCargaHoraria', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCurvaCargaHoraria', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCurvaCargaHoraria', () => {
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

    describe('addOnsCurvaCargaHorariaToCollectionIfMissing', () => {
      it('should add a OnsCurvaCargaHoraria to an empty array', () => {
        const onsCurvaCargaHoraria: IOnsCurvaCargaHoraria = sampleWithRequiredData;
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing([], onsCurvaCargaHoraria);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCurvaCargaHoraria);
      });

      it('should not add a OnsCurvaCargaHoraria to an array that contains it', () => {
        const onsCurvaCargaHoraria: IOnsCurvaCargaHoraria = sampleWithRequiredData;
        const onsCurvaCargaHorariaCollection: IOnsCurvaCargaHoraria[] = [
          {
            ...onsCurvaCargaHoraria,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing(onsCurvaCargaHorariaCollection, onsCurvaCargaHoraria);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCurvaCargaHoraria to an array that doesn't contain it", () => {
        const onsCurvaCargaHoraria: IOnsCurvaCargaHoraria = sampleWithRequiredData;
        const onsCurvaCargaHorariaCollection: IOnsCurvaCargaHoraria[] = [sampleWithPartialData];
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing(onsCurvaCargaHorariaCollection, onsCurvaCargaHoraria);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCurvaCargaHoraria);
      });

      it('should add only unique OnsCurvaCargaHoraria to an array', () => {
        const onsCurvaCargaHorariaArray: IOnsCurvaCargaHoraria[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsCurvaCargaHorariaCollection: IOnsCurvaCargaHoraria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing(onsCurvaCargaHorariaCollection, ...onsCurvaCargaHorariaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCurvaCargaHoraria: IOnsCurvaCargaHoraria = sampleWithRequiredData;
        const onsCurvaCargaHoraria2: IOnsCurvaCargaHoraria = sampleWithPartialData;
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing([], onsCurvaCargaHoraria, onsCurvaCargaHoraria2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCurvaCargaHoraria);
        expect(expectedResult).toContain(onsCurvaCargaHoraria2);
      });

      it('should accept null and undefined values', () => {
        const onsCurvaCargaHoraria: IOnsCurvaCargaHoraria = sampleWithRequiredData;
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing([], null, onsCurvaCargaHoraria, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCurvaCargaHoraria);
      });

      it('should return initial array if no OnsCurvaCargaHoraria is added', () => {
        const onsCurvaCargaHorariaCollection: IOnsCurvaCargaHoraria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCurvaCargaHorariaToCollectionIfMissing(onsCurvaCargaHorariaCollection, undefined, null);
        expect(expectedResult).toEqual(onsCurvaCargaHorariaCollection);
      });
    });

    describe('compareOnsCurvaCargaHoraria', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCurvaCargaHoraria(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 21772 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCurvaCargaHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsCurvaCargaHoraria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 21772 };
        const entity2 = { id: 2557 };

        const compareResult1 = service.compareOnsCurvaCargaHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsCurvaCargaHoraria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 21772 };
        const entity2 = { id: 21772 };

        const compareResult1 = service.compareOnsCurvaCargaHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsCurvaCargaHoraria(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
