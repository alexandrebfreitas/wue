import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-carga-energia-mensal.test-samples';

import { OnsCargaEnergiaMensalFormService } from './ons-carga-energia-mensal-form.service';

describe('OnsCargaEnergiaMensal Form Service', () => {
  let service: OnsCargaEnergiaMensalFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCargaEnergiaMensalFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCargaEnergiaMensalFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCargaenergiamwmed: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCargaEnergiaMensal should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCargaenergiamwmed: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCargaEnergiaMensal', () => {
      it('should return NewOnsCargaEnergiaMensal for default OnsCargaEnergiaMensal initial value', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup(sampleWithNewData);

        const onsCargaEnergiaMensal = service.getOnsCargaEnergiaMensal(formGroup) as any;

        expect(onsCargaEnergiaMensal).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCargaEnergiaMensal for empty OnsCargaEnergiaMensal initial value', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup();

        const onsCargaEnergiaMensal = service.getOnsCargaEnergiaMensal(formGroup) as any;

        expect(onsCargaEnergiaMensal).toMatchObject({});
      });

      it('should return IOnsCargaEnergiaMensal', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup(sampleWithRequiredData);

        const onsCargaEnergiaMensal = service.getOnsCargaEnergiaMensal(formGroup) as any;

        expect(onsCargaEnergiaMensal).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCargaEnergiaMensal should not enable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCargaEnergiaMensal should disable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaMensalFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
