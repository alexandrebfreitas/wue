import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-reservatorios.test-samples';

import { OnsReservatoriosFormService } from './ons-reservatorios-form.service';

describe('OnsReservatorios Form Service', () => {
  let service: OnsReservatoriosFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsReservatoriosFormService);
  });

  describe('Service methods', () => {
    describe('createOnsReservatoriosFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsReservatoriosFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomRee: expect.any(Object),
            datEntrada: expect.any(Object),
            valCotamaxima: expect.any(Object),
            valCotaminima: expect.any(Object),
            valVolmax: expect.any(Object),
            valVolmin: expect.any(Object),
            valVolutiltot: expect.any(Object),
            valProdutibilidadeespecifica: expect.any(Object),
            valProdutividade65volutil: expect.any(Object),
            valTipoperda: expect.any(Object),
            valPerda: expect.any(Object),
            valLatitude: expect.any(Object),
            valLongitude: expect.any(Object),
            idReservatorio: expect.any(Object),
          }),
        );
      });

      it('passing IOnsReservatorios should create a new form with FormGroup', () => {
        const formGroup = service.createOnsReservatoriosFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomRee: expect.any(Object),
            datEntrada: expect.any(Object),
            valCotamaxima: expect.any(Object),
            valCotaminima: expect.any(Object),
            valVolmax: expect.any(Object),
            valVolmin: expect.any(Object),
            valVolutiltot: expect.any(Object),
            valProdutibilidadeespecifica: expect.any(Object),
            valProdutividade65volutil: expect.any(Object),
            valTipoperda: expect.any(Object),
            valPerda: expect.any(Object),
            valLatitude: expect.any(Object),
            valLongitude: expect.any(Object),
            idReservatorio: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsReservatorios', () => {
      it('should return NewOnsReservatorios for default OnsReservatorios initial value', () => {
        const formGroup = service.createOnsReservatoriosFormGroup(sampleWithNewData);

        const onsReservatorios = service.getOnsReservatorios(formGroup) as any;

        expect(onsReservatorios).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsReservatorios for empty OnsReservatorios initial value', () => {
        const formGroup = service.createOnsReservatoriosFormGroup();

        const onsReservatorios = service.getOnsReservatorios(formGroup) as any;

        expect(onsReservatorios).toMatchObject({});
      });

      it('should return IOnsReservatorios', () => {
        const formGroup = service.createOnsReservatoriosFormGroup(sampleWithRequiredData);

        const onsReservatorios = service.getOnsReservatorios(formGroup) as any;

        expect(onsReservatorios).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsReservatorios should not enable id FormControl', () => {
        const formGroup = service.createOnsReservatoriosFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsReservatorios should disable id FormControl', () => {
        const formGroup = service.createOnsReservatoriosFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
