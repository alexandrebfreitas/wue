import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDescontinuadoPrecipitacaoDiariaObservada } from '../ons-descontinuado-precipitacao-diaria-observada.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-descontinuado-precipitacao-diaria-observada.test-samples';

import {
  OnsDescontinuadoPrecipitacaoDiariaObservadaService,
  RestOnsDescontinuadoPrecipitacaoDiariaObservada,
} from './ons-descontinuado-precipitacao-diaria-observada.service';

const requireRestSample: RestOnsDescontinuadoPrecipitacaoDiariaObservada = {
  ...sampleWithRequiredData,
  datObservada: sampleWithRequiredData.datObservada?.format(DATE_FORMAT),
};

describe('OnsDescontinuadoPrecipitacaoDiariaObservada Service', () => {
  let service: OnsDescontinuadoPrecipitacaoDiariaObservadaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsDescontinuadoPrecipitacaoDiariaObservada | IOnsDescontinuadoPrecipitacaoDiariaObservada[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDescontinuadoPrecipitacaoDiariaObservadaService);
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

    it('should create a OnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      const onsDescontinuadoPrecipitacaoDiariaObservada = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDescontinuadoPrecipitacaoDiariaObservada).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      const onsDescontinuadoPrecipitacaoDiariaObservada = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDescontinuadoPrecipitacaoDiariaObservada).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDescontinuadoPrecipitacaoDiariaObservada', () => {
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

    describe('addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing', () => {
      it('should add a OnsDescontinuadoPrecipitacaoDiariaObservada to an empty array', () => {
        const onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada = sampleWithRequiredData;
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          [],
          onsDescontinuadoPrecipitacaoDiariaObservada,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDescontinuadoPrecipitacaoDiariaObservada);
      });

      it('should not add a OnsDescontinuadoPrecipitacaoDiariaObservada to an array that contains it', () => {
        const onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada = sampleWithRequiredData;
        const onsDescontinuadoPrecipitacaoDiariaObservadaCollection: IOnsDescontinuadoPrecipitacaoDiariaObservada[] = [
          {
            ...onsDescontinuadoPrecipitacaoDiariaObservada,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          onsDescontinuadoPrecipitacaoDiariaObservadaCollection,
          onsDescontinuadoPrecipitacaoDiariaObservada,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDescontinuadoPrecipitacaoDiariaObservada to an array that doesn't contain it", () => {
        const onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada = sampleWithRequiredData;
        const onsDescontinuadoPrecipitacaoDiariaObservadaCollection: IOnsDescontinuadoPrecipitacaoDiariaObservada[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          onsDescontinuadoPrecipitacaoDiariaObservadaCollection,
          onsDescontinuadoPrecipitacaoDiariaObservada,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDescontinuadoPrecipitacaoDiariaObservada);
      });

      it('should add only unique OnsDescontinuadoPrecipitacaoDiariaObservada to an array', () => {
        const onsDescontinuadoPrecipitacaoDiariaObservadaArray: IOnsDescontinuadoPrecipitacaoDiariaObservada[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDescontinuadoPrecipitacaoDiariaObservadaCollection: IOnsDescontinuadoPrecipitacaoDiariaObservada[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          onsDescontinuadoPrecipitacaoDiariaObservadaCollection,
          ...onsDescontinuadoPrecipitacaoDiariaObservadaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada = sampleWithRequiredData;
        const onsDescontinuadoPrecipitacaoDiariaObservada2: IOnsDescontinuadoPrecipitacaoDiariaObservada = sampleWithPartialData;
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          [],
          onsDescontinuadoPrecipitacaoDiariaObservada,
          onsDescontinuadoPrecipitacaoDiariaObservada2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDescontinuadoPrecipitacaoDiariaObservada);
        expect(expectedResult).toContain(onsDescontinuadoPrecipitacaoDiariaObservada2);
      });

      it('should accept null and undefined values', () => {
        const onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada = sampleWithRequiredData;
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          [],
          null,
          onsDescontinuadoPrecipitacaoDiariaObservada,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDescontinuadoPrecipitacaoDiariaObservada);
      });

      it('should return initial array if no OnsDescontinuadoPrecipitacaoDiariaObservada is added', () => {
        const onsDescontinuadoPrecipitacaoDiariaObservadaCollection: IOnsDescontinuadoPrecipitacaoDiariaObservada[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing(
          onsDescontinuadoPrecipitacaoDiariaObservadaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDescontinuadoPrecipitacaoDiariaObservadaCollection);
      });
    });

    describe('compareOnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 5142 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity1, entity2);
        const compareResult2 = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 5142 };
        const entity2 = { id: 8014 };

        const compareResult1 = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity1, entity2);
        const compareResult2 = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 5142 };
        const entity2 = { id: 5142 };

        const compareResult1 = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity1, entity2);
        const compareResult2 = service.compareOnsDescontinuadoPrecipitacaoDiariaObservada(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
