import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.test-samples';

import {
  OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService,
  RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
} from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';

const requireRestSample: RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = {
  ...sampleWithRequiredData,
  datProgramacao: sampleWithRequiredData.datProgramacao?.format(DATE_FORMAT),
};

describe('OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares Service', () => {
  let service: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService;
  let httpMock: HttpTestingController;
  let expectedResult:
    | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
    | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]
    | boolean
    | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService);
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

    it('should create a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
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

    describe('addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing', () => {
      it('should add a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares to an empty array', () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          [],
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
      });

      it('should not add a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares to an array that contains it', () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
          sampleWithRequiredData;
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[] =
          [
            {
              ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
            },
            sampleWithPartialData,
          ];
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection,
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares to an array that doesn't contain it", () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
          sampleWithRequiredData;
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[] =
          [sampleWithPartialData];
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection,
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
      });

      it('should add only unique OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares to an array', () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresArray: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection,
          ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
          sampleWithRequiredData;
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares2: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
          sampleWithPartialData;
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          [],
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolares2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
        expect(expectedResult).toContain(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares2);
      });

      it('should accept null and undefined values', () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
          sampleWithRequiredData;
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          [],
          null,
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
      });

      it('should return initial array if no OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares is added', () => {
        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[] =
          [sampleWithRequiredData];
        expectedResult = service.addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing(
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection);
      });
    });

    describe('compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 3628 };
        const entity2 = null;

        const compareResult1 = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity1, entity2);
        const compareResult2 = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 3628 };
        const entity2 = { id: 11822 };

        const compareResult1 = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity1, entity2);
        const compareResult2 = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 3628 };
        const entity2 = { id: 3628 };

        const compareResult1 = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity1, entity2);
        const compareResult2 = service.compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
