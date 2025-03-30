import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ear-diario-ree-reservatorio-equivalente-energia.test-samples';

import {
  OnsEarDiarioReeReservatorioEquivalenteEnergiaService,
  RestOnsEarDiarioReeReservatorioEquivalenteEnergia,
} from './ons-ear-diario-ree-reservatorio-equivalente-energia.service';

const requireRestSample: RestOnsEarDiarioReeReservatorioEquivalenteEnergia = {
  ...sampleWithRequiredData,
  earData: sampleWithRequiredData.earData?.format(DATE_FORMAT),
};

describe('OnsEarDiarioReeReservatorioEquivalenteEnergia Service', () => {
  let service: OnsEarDiarioReeReservatorioEquivalenteEnergiaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEarDiarioReeReservatorioEquivalenteEnergia | IOnsEarDiarioReeReservatorioEquivalenteEnergia[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaService);
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

    it('should create a OnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      const onsEarDiarioReeReservatorioEquivalenteEnergia = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEarDiarioReeReservatorioEquivalenteEnergia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      const onsEarDiarioReeReservatorioEquivalenteEnergia = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEarDiarioReeReservatorioEquivalenteEnergia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
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

    describe('addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing', () => {
      it('should add a OnsEarDiarioReeReservatorioEquivalenteEnergia to an empty array', () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          [],
          onsEarDiarioReeReservatorioEquivalenteEnergia,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioReeReservatorioEquivalenteEnergia);
      });

      it('should not add a OnsEarDiarioReeReservatorioEquivalenteEnergia to an array that contains it', () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        const onsEarDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEarDiarioReeReservatorioEquivalenteEnergia[] = [
          {
            ...onsEarDiarioReeReservatorioEquivalenteEnergia,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEarDiarioReeReservatorioEquivalenteEnergiaCollection,
          onsEarDiarioReeReservatorioEquivalenteEnergia,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEarDiarioReeReservatorioEquivalenteEnergia to an array that doesn't contain it", () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        const onsEarDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEarDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEarDiarioReeReservatorioEquivalenteEnergiaCollection,
          onsEarDiarioReeReservatorioEquivalenteEnergia,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioReeReservatorioEquivalenteEnergia);
      });

      it('should add only unique OnsEarDiarioReeReservatorioEquivalenteEnergia to an array', () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergiaArray: IOnsEarDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsEarDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEarDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEarDiarioReeReservatorioEquivalenteEnergiaCollection,
          ...onsEarDiarioReeReservatorioEquivalenteEnergiaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        const onsEarDiarioReeReservatorioEquivalenteEnergia2: IOnsEarDiarioReeReservatorioEquivalenteEnergia = sampleWithPartialData;
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          [],
          onsEarDiarioReeReservatorioEquivalenteEnergia,
          onsEarDiarioReeReservatorioEquivalenteEnergia2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEarDiarioReeReservatorioEquivalenteEnergia);
        expect(expectedResult).toContain(onsEarDiarioReeReservatorioEquivalenteEnergia2);
      });

      it('should accept null and undefined values', () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          [],
          null,
          onsEarDiarioReeReservatorioEquivalenteEnergia,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEarDiarioReeReservatorioEquivalenteEnergia);
      });

      it('should return initial array if no OnsEarDiarioReeReservatorioEquivalenteEnergia is added', () => {
        const onsEarDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEarDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEarDiarioReeReservatorioEquivalenteEnergiaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsEarDiarioReeReservatorioEquivalenteEnergiaCollection);
      });
    });

    describe('compareOnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 21274 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 21274 };
        const entity2 = { id: 16684 };

        const compareResult1 = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 21274 };
        const entity2 = { id: 21274 };

        const compareResult1 = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);
        const compareResult2 = service.compareOnsEarDiarioReeReservatorioEquivalenteEnergia(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
