import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-carga-energia-verificada.test-samples';

import { OnsCargaEnergiaVerificadaFormService } from './ons-carga-energia-verificada-form.service';

describe('OnsCargaEnergiaVerificada Form Service', () => {
  let service: OnsCargaEnergiaVerificadaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCargaEnergiaVerificadaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCargaEnergiaVerificadaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codAreacarga: expect.any(Object),
            datReferencia: expect.any(Object),
            dinReferenciautc: expect.any(Object),
            valCargaglobal: expect.any(Object),
            valCargaglobalsmmg: expect.any(Object),
            valCargammgd: expect.any(Object),
            valCargaglobalcons: expect.any(Object),
            valConsistencia: expect.any(Object),
            valCargasupervisionada: expect.any(Object),
            valCarganaosupervisionada: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCargaEnergiaVerificada should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codAreacarga: expect.any(Object),
            datReferencia: expect.any(Object),
            dinReferenciautc: expect.any(Object),
            valCargaglobal: expect.any(Object),
            valCargaglobalsmmg: expect.any(Object),
            valCargammgd: expect.any(Object),
            valCargaglobalcons: expect.any(Object),
            valConsistencia: expect.any(Object),
            valCargasupervisionada: expect.any(Object),
            valCarganaosupervisionada: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCargaEnergiaVerificada', () => {
      it('should return NewOnsCargaEnergiaVerificada for default OnsCargaEnergiaVerificada initial value', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup(sampleWithNewData);

        const onsCargaEnergiaVerificada = service.getOnsCargaEnergiaVerificada(formGroup) as any;

        expect(onsCargaEnergiaVerificada).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCargaEnergiaVerificada for empty OnsCargaEnergiaVerificada initial value', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup();

        const onsCargaEnergiaVerificada = service.getOnsCargaEnergiaVerificada(formGroup) as any;

        expect(onsCargaEnergiaVerificada).toMatchObject({});
      });

      it('should return IOnsCargaEnergiaVerificada', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup(sampleWithRequiredData);

        const onsCargaEnergiaVerificada = service.getOnsCargaEnergiaVerificada(formGroup) as any;

        expect(onsCargaEnergiaVerificada).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCargaEnergiaVerificada should not enable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCargaEnergiaVerificada should disable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaVerificadaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
