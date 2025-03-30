import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-geracao-comercial-para-exportacao-internacional.test-samples';

import {
  OnsGeracaoComercialParaExportacaoInternacionalService,
  RestOnsGeracaoComercialParaExportacaoInternacional,
} from './ons-geracao-comercial-para-exportacao-internacional.service';

const requireRestSample: RestOnsGeracaoComercialParaExportacaoInternacional = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsGeracaoComercialParaExportacaoInternacional Service', () => {
  let service: OnsGeracaoComercialParaExportacaoInternacionalService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsGeracaoComercialParaExportacaoInternacional | IOnsGeracaoComercialParaExportacaoInternacional[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsGeracaoComercialParaExportacaoInternacionalService);
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

    it('should create a OnsGeracaoComercialParaExportacaoInternacional', () => {
      const onsGeracaoComercialParaExportacaoInternacional = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsGeracaoComercialParaExportacaoInternacional).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsGeracaoComercialParaExportacaoInternacional', () => {
      const onsGeracaoComercialParaExportacaoInternacional = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsGeracaoComercialParaExportacaoInternacional).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsGeracaoComercialParaExportacaoInternacional', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsGeracaoComercialParaExportacaoInternacional', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsGeracaoComercialParaExportacaoInternacional', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsGeracaoComercialParaExportacaoInternacional', () => {
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

    describe('addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing', () => {
      it('should add a OnsGeracaoComercialParaExportacaoInternacional to an empty array', () => {
        const onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional = sampleWithRequiredData;
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          [],
          onsGeracaoComercialParaExportacaoInternacional,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsGeracaoComercialParaExportacaoInternacional);
      });

      it('should not add a OnsGeracaoComercialParaExportacaoInternacional to an array that contains it', () => {
        const onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional = sampleWithRequiredData;
        const onsGeracaoComercialParaExportacaoInternacionalCollection: IOnsGeracaoComercialParaExportacaoInternacional[] = [
          {
            ...onsGeracaoComercialParaExportacaoInternacional,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          onsGeracaoComercialParaExportacaoInternacionalCollection,
          onsGeracaoComercialParaExportacaoInternacional,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsGeracaoComercialParaExportacaoInternacional to an array that doesn't contain it", () => {
        const onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional = sampleWithRequiredData;
        const onsGeracaoComercialParaExportacaoInternacionalCollection: IOnsGeracaoComercialParaExportacaoInternacional[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          onsGeracaoComercialParaExportacaoInternacionalCollection,
          onsGeracaoComercialParaExportacaoInternacional,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsGeracaoComercialParaExportacaoInternacional);
      });

      it('should add only unique OnsGeracaoComercialParaExportacaoInternacional to an array', () => {
        const onsGeracaoComercialParaExportacaoInternacionalArray: IOnsGeracaoComercialParaExportacaoInternacional[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsGeracaoComercialParaExportacaoInternacionalCollection: IOnsGeracaoComercialParaExportacaoInternacional[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          onsGeracaoComercialParaExportacaoInternacionalCollection,
          ...onsGeracaoComercialParaExportacaoInternacionalArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional = sampleWithRequiredData;
        const onsGeracaoComercialParaExportacaoInternacional2: IOnsGeracaoComercialParaExportacaoInternacional = sampleWithPartialData;
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          [],
          onsGeracaoComercialParaExportacaoInternacional,
          onsGeracaoComercialParaExportacaoInternacional2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsGeracaoComercialParaExportacaoInternacional);
        expect(expectedResult).toContain(onsGeracaoComercialParaExportacaoInternacional2);
      });

      it('should accept null and undefined values', () => {
        const onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional = sampleWithRequiredData;
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          [],
          null,
          onsGeracaoComercialParaExportacaoInternacional,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsGeracaoComercialParaExportacaoInternacional);
      });

      it('should return initial array if no OnsGeracaoComercialParaExportacaoInternacional is added', () => {
        const onsGeracaoComercialParaExportacaoInternacionalCollection: IOnsGeracaoComercialParaExportacaoInternacional[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing(
          onsGeracaoComercialParaExportacaoInternacionalCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsGeracaoComercialParaExportacaoInternacionalCollection);
      });
    });

    describe('compareOnsGeracaoComercialParaExportacaoInternacional', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 18041 };
        const entity2 = null;

        const compareResult1 = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 18041 };
        const entity2 = { id: 32650 };

        const compareResult1 = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 18041 };
        const entity2 = { id: 18041 };

        const compareResult1 = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity1, entity2);
        const compareResult2 = service.compareOnsGeracaoComercialParaExportacaoInternacional(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
