import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-balanco-energia-nos-subsistemas.test-samples';

import { OnsBalancoEnergiaNosSubsistemasFormService } from './ons-balanco-energia-nos-subsistemas-form.service';

describe('OnsBalancoEnergiaNosSubsistemas Form Service', () => {
  let service: OnsBalancoEnergiaNosSubsistemasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsBalancoEnergiaNosSubsistemasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsBalancoEnergiaNosSubsistemasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valGerhidraulica: expect.any(Object),
            valGertermica: expect.any(Object),
            valGereolica: expect.any(Object),
            valGersolar: expect.any(Object),
            valCarga: expect.any(Object),
            valIntercambio: expect.any(Object),
          }),
        );
      });

      it('passing IOnsBalancoEnergiaNosSubsistemas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valGerhidraulica: expect.any(Object),
            valGertermica: expect.any(Object),
            valGereolica: expect.any(Object),
            valGersolar: expect.any(Object),
            valCarga: expect.any(Object),
            valIntercambio: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsBalancoEnergiaNosSubsistemas', () => {
      it('should return NewOnsBalancoEnergiaNosSubsistemas for default OnsBalancoEnergiaNosSubsistemas initial value', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup(sampleWithNewData);

        const onsBalancoEnergiaNosSubsistemas = service.getOnsBalancoEnergiaNosSubsistemas(formGroup) as any;

        expect(onsBalancoEnergiaNosSubsistemas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsBalancoEnergiaNosSubsistemas for empty OnsBalancoEnergiaNosSubsistemas initial value', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup();

        const onsBalancoEnergiaNosSubsistemas = service.getOnsBalancoEnergiaNosSubsistemas(formGroup) as any;

        expect(onsBalancoEnergiaNosSubsistemas).toMatchObject({});
      });

      it('should return IOnsBalancoEnergiaNosSubsistemas', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup(sampleWithRequiredData);

        const onsBalancoEnergiaNosSubsistemas = service.getOnsBalancoEnergiaNosSubsistemas(formGroup) as any;

        expect(onsBalancoEnergiaNosSubsistemas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsBalancoEnergiaNosSubsistemas should not enable id FormControl', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsBalancoEnergiaNosSubsistemas should disable id FormControl', () => {
        const formGroup = service.createOnsBalancoEnergiaNosSubsistemasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
