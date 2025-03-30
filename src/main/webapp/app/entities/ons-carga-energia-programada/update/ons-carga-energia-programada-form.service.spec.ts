import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-carga-energia-programada.test-samples';

import { OnsCargaEnergiaProgramadaFormService } from './ons-carga-energia-programada-form.service';

describe('OnsCargaEnergiaProgramada Form Service', () => {
  let service: OnsCargaEnergiaProgramadaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCargaEnergiaProgramadaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCargaEnergiaProgramadaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codAreacarga: expect.any(Object),
            datReferencia: expect.any(Object),
            dinReferenciautc: expect.any(Object),
            valCargaglobalprogramada: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCargaEnergiaProgramada should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codAreacarga: expect.any(Object),
            datReferencia: expect.any(Object),
            dinReferenciautc: expect.any(Object),
            valCargaglobalprogramada: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCargaEnergiaProgramada', () => {
      it('should return NewOnsCargaEnergiaProgramada for default OnsCargaEnergiaProgramada initial value', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup(sampleWithNewData);

        const onsCargaEnergiaProgramada = service.getOnsCargaEnergiaProgramada(formGroup) as any;

        expect(onsCargaEnergiaProgramada).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCargaEnergiaProgramada for empty OnsCargaEnergiaProgramada initial value', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup();

        const onsCargaEnergiaProgramada = service.getOnsCargaEnergiaProgramada(formGroup) as any;

        expect(onsCargaEnergiaProgramada).toMatchObject({});
      });

      it('should return IOnsCargaEnergiaProgramada', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup(sampleWithRequiredData);

        const onsCargaEnergiaProgramada = service.getOnsCargaEnergiaProgramada(formGroup) as any;

        expect(onsCargaEnergiaProgramada).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCargaEnergiaProgramada should not enable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCargaEnergiaProgramada should disable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaProgramadaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
