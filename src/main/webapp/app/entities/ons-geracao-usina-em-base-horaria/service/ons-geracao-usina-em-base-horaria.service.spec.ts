import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-geracao-usina-em-base-horaria.test-samples';

import { OnsGeracaoUsinaEmBaseHorariaService, RestOnsGeracaoUsinaEmBaseHoraria } from './ons-geracao-usina-em-base-horaria.service';

const requireRestSample: RestOnsGeracaoUsinaEmBaseHoraria = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsGeracaoUsinaEmBaseHoraria Service', () => {
  let service: OnsGeracaoUsinaEmBaseHorariaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsGeracaoUsinaEmBaseHoraria | IOnsGeracaoUsinaEmBaseHoraria[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsGeracaoUsinaEmBaseHorariaService);
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

    it('should create a OnsGeracaoUsinaEmBaseHoraria', () => {
      const onsGeracaoUsinaEmBaseHoraria = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsGeracaoUsinaEmBaseHoraria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsGeracaoUsinaEmBaseHoraria', () => {
      const onsGeracaoUsinaEmBaseHoraria = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsGeracaoUsinaEmBaseHoraria).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsGeracaoUsinaEmBaseHoraria', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsGeracaoUsinaEmBaseHoraria', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsGeracaoUsinaEmBaseHoraria', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsGeracaoUsinaEmBaseHoraria', () => {
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

    describe('addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing', () => {
      it('should add a OnsGeracaoUsinaEmBaseHoraria to an empty array', () => {
        const onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria = sampleWithRequiredData;
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing([], onsGeracaoUsinaEmBaseHoraria);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsGeracaoUsinaEmBaseHoraria);
      });

      it('should not add a OnsGeracaoUsinaEmBaseHoraria to an array that contains it', () => {
        const onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria = sampleWithRequiredData;
        const onsGeracaoUsinaEmBaseHorariaCollection: IOnsGeracaoUsinaEmBaseHoraria[] = [
          {
            ...onsGeracaoUsinaEmBaseHoraria,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing(
          onsGeracaoUsinaEmBaseHorariaCollection,
          onsGeracaoUsinaEmBaseHoraria,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsGeracaoUsinaEmBaseHoraria to an array that doesn't contain it", () => {
        const onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria = sampleWithRequiredData;
        const onsGeracaoUsinaEmBaseHorariaCollection: IOnsGeracaoUsinaEmBaseHoraria[] = [sampleWithPartialData];
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing(
          onsGeracaoUsinaEmBaseHorariaCollection,
          onsGeracaoUsinaEmBaseHoraria,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsGeracaoUsinaEmBaseHoraria);
      });

      it('should add only unique OnsGeracaoUsinaEmBaseHoraria to an array', () => {
        const onsGeracaoUsinaEmBaseHorariaArray: IOnsGeracaoUsinaEmBaseHoraria[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsGeracaoUsinaEmBaseHorariaCollection: IOnsGeracaoUsinaEmBaseHoraria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing(
          onsGeracaoUsinaEmBaseHorariaCollection,
          ...onsGeracaoUsinaEmBaseHorariaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria = sampleWithRequiredData;
        const onsGeracaoUsinaEmBaseHoraria2: IOnsGeracaoUsinaEmBaseHoraria = sampleWithPartialData;
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing(
          [],
          onsGeracaoUsinaEmBaseHoraria,
          onsGeracaoUsinaEmBaseHoraria2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsGeracaoUsinaEmBaseHoraria);
        expect(expectedResult).toContain(onsGeracaoUsinaEmBaseHoraria2);
      });

      it('should accept null and undefined values', () => {
        const onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria = sampleWithRequiredData;
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing([], null, onsGeracaoUsinaEmBaseHoraria, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsGeracaoUsinaEmBaseHoraria);
      });

      it('should return initial array if no OnsGeracaoUsinaEmBaseHoraria is added', () => {
        const onsGeracaoUsinaEmBaseHorariaCollection: IOnsGeracaoUsinaEmBaseHoraria[] = [sampleWithRequiredData];
        expectedResult = service.addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing(
          onsGeracaoUsinaEmBaseHorariaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsGeracaoUsinaEmBaseHorariaCollection);
      });
    });

    describe('compareOnsGeracaoUsinaEmBaseHoraria', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsGeracaoUsinaEmBaseHoraria(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 22430 };
        const entity2 = null;

        const compareResult1 = service.compareOnsGeracaoUsinaEmBaseHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoUsinaEmBaseHoraria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 22430 };
        const entity2 = { id: 5320 };

        const compareResult1 = service.compareOnsGeracaoUsinaEmBaseHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoUsinaEmBaseHoraria(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 22430 };
        const entity2 = { id: 22430 };

        const compareResult1 = service.compareOnsGeracaoUsinaEmBaseHoraria(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoUsinaEmBaseHoraria(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
