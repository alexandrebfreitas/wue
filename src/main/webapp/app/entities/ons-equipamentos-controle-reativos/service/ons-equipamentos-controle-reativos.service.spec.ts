import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-equipamentos-controle-reativos.test-samples';

import { OnsEquipamentosControleReativosService, RestOnsEquipamentosControleReativos } from './ons-equipamentos-controle-reativos.service';

const requireRestSample: RestOnsEquipamentosControleReativos = {
  ...sampleWithRequiredData,
  datEntradaoperacao: sampleWithRequiredData.datEntradaoperacao?.format(DATE_FORMAT),
  datDesativacao: sampleWithRequiredData.datDesativacao?.format(DATE_FORMAT),
};

describe('OnsEquipamentosControleReativos Service', () => {
  let service: OnsEquipamentosControleReativosService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEquipamentosControleReativos | IOnsEquipamentosControleReativos[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEquipamentosControleReativosService);
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

    it('should create a OnsEquipamentosControleReativos', () => {
      const onsEquipamentosControleReativos = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEquipamentosControleReativos).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEquipamentosControleReativos', () => {
      const onsEquipamentosControleReativos = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEquipamentosControleReativos).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEquipamentosControleReativos', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEquipamentosControleReativos', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEquipamentosControleReativos', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEquipamentosControleReativos', () => {
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

    describe('addOnsEquipamentosControleReativosToCollectionIfMissing', () => {
      it('should add a OnsEquipamentosControleReativos to an empty array', () => {
        const onsEquipamentosControleReativos: IOnsEquipamentosControleReativos = sampleWithRequiredData;
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing([], onsEquipamentosControleReativos);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEquipamentosControleReativos);
      });

      it('should not add a OnsEquipamentosControleReativos to an array that contains it', () => {
        const onsEquipamentosControleReativos: IOnsEquipamentosControleReativos = sampleWithRequiredData;
        const onsEquipamentosControleReativosCollection: IOnsEquipamentosControleReativos[] = [
          {
            ...onsEquipamentosControleReativos,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing(
          onsEquipamentosControleReativosCollection,
          onsEquipamentosControleReativos,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEquipamentosControleReativos to an array that doesn't contain it", () => {
        const onsEquipamentosControleReativos: IOnsEquipamentosControleReativos = sampleWithRequiredData;
        const onsEquipamentosControleReativosCollection: IOnsEquipamentosControleReativos[] = [sampleWithPartialData];
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing(
          onsEquipamentosControleReativosCollection,
          onsEquipamentosControleReativos,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEquipamentosControleReativos);
      });

      it('should add only unique OnsEquipamentosControleReativos to an array', () => {
        const onsEquipamentosControleReativosArray: IOnsEquipamentosControleReativos[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsEquipamentosControleReativosCollection: IOnsEquipamentosControleReativos[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing(
          onsEquipamentosControleReativosCollection,
          ...onsEquipamentosControleReativosArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEquipamentosControleReativos: IOnsEquipamentosControleReativos = sampleWithRequiredData;
        const onsEquipamentosControleReativos2: IOnsEquipamentosControleReativos = sampleWithPartialData;
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing(
          [],
          onsEquipamentosControleReativos,
          onsEquipamentosControleReativos2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEquipamentosControleReativos);
        expect(expectedResult).toContain(onsEquipamentosControleReativos2);
      });

      it('should accept null and undefined values', () => {
        const onsEquipamentosControleReativos: IOnsEquipamentosControleReativos = sampleWithRequiredData;
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing(
          [],
          null,
          onsEquipamentosControleReativos,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEquipamentosControleReativos);
      });

      it('should return initial array if no OnsEquipamentosControleReativos is added', () => {
        const onsEquipamentosControleReativosCollection: IOnsEquipamentosControleReativos[] = [sampleWithRequiredData];
        expectedResult = service.addOnsEquipamentosControleReativosToCollectionIfMissing(
          onsEquipamentosControleReativosCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsEquipamentosControleReativosCollection);
      });
    });

    describe('compareOnsEquipamentosControleReativos', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEquipamentosControleReativos(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 122 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEquipamentosControleReativos(entity1, entity2);
        const compareResult2 = service.compareOnsEquipamentosControleReativos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 122 };
        const entity2 = { id: 17574 };

        const compareResult1 = service.compareOnsEquipamentosControleReativos(entity1, entity2);
        const compareResult2 = service.compareOnsEquipamentosControleReativos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 122 };
        const entity2 = { id: 122 };

        const compareResult1 = service.compareOnsEquipamentosControleReativos(entity1, entity2);
        const compareResult2 = service.compareOnsEquipamentosControleReativos(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
