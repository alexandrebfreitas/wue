import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.test-samples';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';

const requireRestSample: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = {
  ...sampleWithRequiredData,
};

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 Service', () => {
  let service: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
    | IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service);
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

    it('should create a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
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

    describe('addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing', () => {
      it('should add a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 to an empty array', () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          [],
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024);
      });

      it('should not add a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 to an array that contains it', () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          sampleWithRequiredData;
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] =
          [
            {
              ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection,
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 to an array that doesn't contain it", () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          sampleWithRequiredData;
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection,
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024);
      });

      it('should add only unique OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 to an array', () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Array: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection,
          ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Array,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          sampleWithRequiredData;
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro20242: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          [],
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro20242,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024);
        expect(expectedResult).toContain(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro20242);
      });

      it('should accept null and undefined values', () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          [],
          null,
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024);
      });

      it('should return initial array if no OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 is added', () => {
        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection);
      });
    });

    describe('compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 6420 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 6420 };
        const entity2 = { id: 31479 };

        const compareResult1 = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 6420 };
        const entity2 = { id: 6420 };

        const compareResult1 = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity1, entity2);
        const compareResult2 = service.compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
