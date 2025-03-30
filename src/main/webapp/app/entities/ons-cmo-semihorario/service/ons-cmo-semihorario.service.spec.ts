import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsCmoSemihorario } from '../ons-cmo-semihorario.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../ons-cmo-semihorario.test-samples';

import { OnsCmoSemihorarioService, RestOnsCmoSemihorario } from './ons-cmo-semihorario.service';

const requireRestSample: RestOnsCmoSemihorario = {
  ...sampleWithRequiredData,
  dinInstante: sampleWithRequiredData.dinInstante?.format(DATE_FORMAT),
};

describe('OnsCmoSemihorario Service', () => {
  let service: OnsCmoSemihorarioService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsCmoSemihorario | IOnsCmoSemihorario[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsCmoSemihorarioService);
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

    it('should create a OnsCmoSemihorario', () => {
      const onsCmoSemihorario = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsCmoSemihorario).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsCmoSemihorario', () => {
      const onsCmoSemihorario = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsCmoSemihorario).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsCmoSemihorario', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsCmoSemihorario', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsCmoSemihorario', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsCmoSemihorario', () => {
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

    describe('addOnsCmoSemihorarioToCollectionIfMissing', () => {
      it('should add a OnsCmoSemihorario to an empty array', () => {
        const onsCmoSemihorario: IOnsCmoSemihorario = sampleWithRequiredData;
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing([], onsCmoSemihorario);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCmoSemihorario);
      });

      it('should not add a OnsCmoSemihorario to an array that contains it', () => {
        const onsCmoSemihorario: IOnsCmoSemihorario = sampleWithRequiredData;
        const onsCmoSemihorarioCollection: IOnsCmoSemihorario[] = [
          {
            ...onsCmoSemihorario,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing(onsCmoSemihorarioCollection, onsCmoSemihorario);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsCmoSemihorario to an array that doesn't contain it", () => {
        const onsCmoSemihorario: IOnsCmoSemihorario = sampleWithRequiredData;
        const onsCmoSemihorarioCollection: IOnsCmoSemihorario[] = [sampleWithPartialData];
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing(onsCmoSemihorarioCollection, onsCmoSemihorario);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCmoSemihorario);
      });

      it('should add only unique OnsCmoSemihorario to an array', () => {
        const onsCmoSemihorarioArray: IOnsCmoSemihorario[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const onsCmoSemihorarioCollection: IOnsCmoSemihorario[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing(onsCmoSemihorarioCollection, ...onsCmoSemihorarioArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsCmoSemihorario: IOnsCmoSemihorario = sampleWithRequiredData;
        const onsCmoSemihorario2: IOnsCmoSemihorario = sampleWithPartialData;
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing([], onsCmoSemihorario, onsCmoSemihorario2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsCmoSemihorario);
        expect(expectedResult).toContain(onsCmoSemihorario2);
      });

      it('should accept null and undefined values', () => {
        const onsCmoSemihorario: IOnsCmoSemihorario = sampleWithRequiredData;
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing([], null, onsCmoSemihorario, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsCmoSemihorario);
      });

      it('should return initial array if no OnsCmoSemihorario is added', () => {
        const onsCmoSemihorarioCollection: IOnsCmoSemihorario[] = [sampleWithRequiredData];
        expectedResult = service.addOnsCmoSemihorarioToCollectionIfMissing(onsCmoSemihorarioCollection, undefined, null);
        expect(expectedResult).toEqual(onsCmoSemihorarioCollection);
      });
    });

    describe('compareOnsCmoSemihorario', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsCmoSemihorario(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 15398 };
        const entity2 = null;

        const compareResult1 = service.compareOnsCmoSemihorario(entity1, entity2);
        const compareResult2 = service.compareOnsCmoSemihorario(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 15398 };
        const entity2 = { id: 15015 };

        const compareResult1 = service.compareOnsCmoSemihorario(entity1, entity2);
        const compareResult2 = service.compareOnsCmoSemihorario(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 15398 };
        const entity2 = { id: 15398 };

        const compareResult1 = service.compareOnsCmoSemihorario(entity1, entity2);
        const compareResult2 = service.compareOnsCmoSemihorario(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
