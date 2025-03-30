import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs } from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.test-samples';

import {
  OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService,
  RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
} from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.service';

const requireRestSample: RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = {
  ...sampleWithRequiredData,
  dinReferencia: sampleWithRequiredData.dinReferencia?.format(DATE_FORMAT),
};

describe('OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
    | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService);
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

    it('should create a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
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

    describe('addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing', () => {
      it('should add a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs to an empty array', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          [],
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
      });

      it('should not add a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs to an array that contains it', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[] =
          [
            {
              ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection,
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs to an array that doesn't contain it", () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection,
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
      });

      it('should add only unique OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs to an array', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsArray: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[] =
          [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection,
          ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          sampleWithRequiredData;
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs2: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          sampleWithPartialData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          [],
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs2);
      });

      it('should accept null and undefined values', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          sampleWithRequiredData;
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          [],
          null,
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
      });

      it('should return initial array if no OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs is added', () => {
        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection);
      });
    });

    describe('compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 8009 };
        const entity2 = null;

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 8009 };
        const entity2 = { id: 285 };

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 8009 };
        const entity2 = { id: 8009 };

        const compareResult1 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity1, entity2);
        const compareResult2 = service.compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
