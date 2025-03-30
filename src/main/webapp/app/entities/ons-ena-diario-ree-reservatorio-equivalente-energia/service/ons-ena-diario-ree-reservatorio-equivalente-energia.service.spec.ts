import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-ena-diario-ree-reservatorio-equivalente-energia.test-samples';

import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from './ons-ena-diario-ree-reservatorio-equivalente-energia.service';

const requireRestSample: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = {
  ...sampleWithRequiredData,
};

describe('OnsEnaDiarioReeReservatorioEquivalenteEnergia Service', () => {
  let service: OnsEnaDiarioReeReservatorioEquivalenteEnergiaService;
  let httpMock: HttpTestingController;
  let expectedResult: IOnsEnaDiarioReeReservatorioEquivalenteEnergia | IOnsEnaDiarioReeReservatorioEquivalenteEnergia[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService);
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

    it('should create a OnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      const onsEnaDiarioReeReservatorioEquivalenteEnergia = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsEnaDiarioReeReservatorioEquivalenteEnergia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      const onsEnaDiarioReeReservatorioEquivalenteEnergia = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsEnaDiarioReeReservatorioEquivalenteEnergia).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
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

    describe('addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing', () => {
      it('should add a OnsEnaDiarioReeReservatorioEquivalenteEnergia to an empty array', () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          [],
          onsEnaDiarioReeReservatorioEquivalenteEnergia,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioReeReservatorioEquivalenteEnergia);
      });

      it('should not add a OnsEnaDiarioReeReservatorioEquivalenteEnergia to an array that contains it', () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        const onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEnaDiarioReeReservatorioEquivalenteEnergia[] = [
          {
            ...onsEnaDiarioReeReservatorioEquivalenteEnergia,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection,
          onsEnaDiarioReeReservatorioEquivalenteEnergia,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsEnaDiarioReeReservatorioEquivalenteEnergia to an array that doesn't contain it", () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        const onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEnaDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithPartialData,
        ];
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection,
          onsEnaDiarioReeReservatorioEquivalenteEnergia,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioReeReservatorioEquivalenteEnergia);
      });

      it('should add only unique OnsEnaDiarioReeReservatorioEquivalenteEnergia to an array', () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergiaArray: IOnsEnaDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEnaDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection,
          ...onsEnaDiarioReeReservatorioEquivalenteEnergiaArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        const onsEnaDiarioReeReservatorioEquivalenteEnergia2: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = sampleWithPartialData;
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          [],
          onsEnaDiarioReeReservatorioEquivalenteEnergia,
          onsEnaDiarioReeReservatorioEquivalenteEnergia2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsEnaDiarioReeReservatorioEquivalenteEnergia);
        expect(expectedResult).toContain(onsEnaDiarioReeReservatorioEquivalenteEnergia2);
      });

      it('should accept null and undefined values', () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = sampleWithRequiredData;
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          [],
          null,
          onsEnaDiarioReeReservatorioEquivalenteEnergia,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsEnaDiarioReeReservatorioEquivalenteEnergia);
      });

      it('should return initial array if no OnsEnaDiarioReeReservatorioEquivalenteEnergia is added', () => {
        const onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection: IOnsEnaDiarioReeReservatorioEquivalenteEnergia[] = [
          sampleWithRequiredData,
        ];
        expectedResult = service.addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing(
          onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection);
      });
    });

    describe('compareOnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 7321 };
        const entity2 = null;

        const compareResult1 = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 7321 };
        const entity2 = { id: 11180 };

        const compareResult1 = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 7321 };
        const entity2 = { id: 7321 };

        const compareResult1 = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity1, entity2);
        const compareResult2 = service.compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
