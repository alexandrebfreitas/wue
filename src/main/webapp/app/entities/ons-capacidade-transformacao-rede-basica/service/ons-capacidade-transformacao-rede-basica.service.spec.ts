import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-capacidade-transformacao-rede-basica.test-samples';

import {
  OnsCapacidadeTransformacaoRedeBasicaService,
  RestOnsCapacidadeTransformacaoRedeBasica,
} from './ons-capacidade-transformacao-rede-basica.service';

const requireRestSample: RestOnsCapacidadeTransformacaoRedeBasica = {
  ...sampleWithRequiredData,
  datEntradaoperacao: sampleWithRequiredData.datEntradaoperacao?.format(DATE_FORMAT),
  datDesativacao: sampleWithRequiredData.datDesativacao?.format(DATE_FORMAT),
};

describe('OnsCapacidadeTransformacaoRedeBasica Service', () => {
  let service: OnsCapacidadeTransformacaoRedeBasicaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCapacidadeTransformacaoRedeBasica | IOnsCapacidadeTransformacaoRedeBasica[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCapacidadeTransformacaoRedeBasicaService);
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

    it('should create a OnsCapacidadeTransformacaoRedeBasica', () => {
      const onsCapacidadeTransformacaoRedeBasica = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCapacidadeTransformacaoRedeBasica).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCapacidadeTransformacaoRedeBasica', () => {
      const onsCapacidadeTransformacaoRedeBasica = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCapacidadeTransformacaoRedeBasica).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCapacidadeTransformacaoRedeBasica', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCapacidadeTransformacaoRedeBasica', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCapacidadeTransformacaoRedeBasica', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCapacidadeTransformacaoRedeBasica', () => {
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

    describe('addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing', () => {
      it('should add a OnsCapacidadeTransformacaoRedeBasica to an empty array', () => {
        const onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica = sampleWithRequiredData;
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing([], onsCapacidadeTransformacaoRedeBasica);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCapacidadeTransformacaoRedeBasica);
      });

      it('should not add a OnsCapacidadeTransformacaoRedeBasica to an array that contains it', () => {
        const onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica = sampleWithRequiredData;
        const onsCapacidadeTransformacaoRedeBasicaCollection: IOnsCapacidadeTransformacaoRedeBasica[] = [
          {
            ...onsCapacidadeTransformacaoRedeBasica,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing(
          onsCapacidadeTransformacaoRedeBasicaCollection,
          onsCapacidadeTransformacaoRedeBasica,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCapacidadeTransformacaoRedeBasica to an array that doesn't contain it", () => {
        const onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica = sampleWithRequiredData;
        const onsCapacidadeTransformacaoRedeBasicaCollection: IOnsCapacidadeTransformacaoRedeBasica[] = [sampleWithPartialData];
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing(
          onsCapacidadeTransformacaoRedeBasicaCollection,
          onsCapacidadeTransformacaoRedeBasica,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCapacidadeTransformacaoRedeBasica);
      });

      it('should add only unique OnsCapacidadeTransformacaoRedeBasica to an array', () => {
        const onsCapacidadeTransformacaoRedeBasicaArray: IOnsCapacidadeTransformacaoRedeBasica[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsCapacidadeTransformacaoRedeBasicaCollection: IOnsCapacidadeTransformacaoRedeBasica[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing(
          onsCapacidadeTransformacaoRedeBasicaCollection,
          ...onsCapacidadeTransformacaoRedeBasicaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica = sampleWithRequiredData;
        const onsCapacidadeTransformacaoRedeBasica2: IOnsCapacidadeTransformacaoRedeBasica = sampleWithPartialData;
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing(
          [],
          onsCapacidadeTransformacaoRedeBasica,
          onsCapacidadeTransformacaoRedeBasica2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCapacidadeTransformacaoRedeBasica);
        expect(expectedResult).toContain(onsCapacidadeTransformacaoRedeBasica2);
      });

      it('should accept null and undefined values', () => {
        const onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica = sampleWithRequiredData;
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing(
          [],
          null,
          onsCapacidadeTransformacaoRedeBasica,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCapacidadeTransformacaoRedeBasica);
      });

      it('should return initial array if no OnsCapacidadeTransformacaoRedeBasica is added', () => {
        const onsCapacidadeTransformacaoRedeBasicaCollection: IOnsCapacidadeTransformacaoRedeBasica[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing(
          onsCapacidadeTransformacaoRedeBasicaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsCapacidadeTransformacaoRedeBasicaCollection);
      });
    });

    describe('compareOnsCapacidadeTransformacaoRedeBasica', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCapacidadeTransformacaoRedeBasica(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 25632 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCapacidadeTransformacaoRedeBasica(entity1, entity2);
        const compareResult2 = service.compareOnsCapacidadeTransformacaoRedeBasica(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 25632 };
        const entity2 = { id: 1198 };

        const compareResult1 = service.compareOnsCapacidadeTransformacaoRedeBasica(entity1, entity2);
        const compareResult2 = service.compareOnsCapacidadeTransformacaoRedeBasica(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 25632 };
        const entity2 = { id: 25632 };

        const compareResult1 = service.compareOnsCapacidadeTransformacaoRedeBasica(entity1, entity2);
        const compareResult2 = service.compareOnsCapacidadeTransformacaoRedeBasica(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
