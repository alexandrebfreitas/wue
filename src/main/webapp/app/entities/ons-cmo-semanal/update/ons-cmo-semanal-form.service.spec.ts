import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-cmo-semanal.test-samples';

import { OnsCmoSemanalFormService } from './ons-cmo-semanal-form.service';

describe('OnsCmoSemanal Form Service', () => {
  let service: OnsCmoSemanalFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCmoSemanalFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCmoSemanalFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCmomediasemanal: expect.any(Object),
            valCmoleve: expect.any(Object),
            valCmomedia: expect.any(Object),
            valCmopesada: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCmoSemanal should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCmomediasemanal: expect.any(Object),
            valCmoleve: expect.any(Object),
            valCmomedia: expect.any(Object),
            valCmopesada: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCmoSemanal', () => {
      it('should return NewOnsCmoSemanal for default OnsCmoSemanal initial value', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup(sampleWithNewData);

        const onsCmoSemanal = service.getOnsCmoSemanal(formGroup) as any;

        expect(onsCmoSemanal).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCmoSemanal for empty OnsCmoSemanal initial value', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup();

        const onsCmoSemanal = service.getOnsCmoSemanal(formGroup) as any;

        expect(onsCmoSemanal).toMatchObject({});
      });

      it('should return IOnsCmoSemanal', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup(sampleWithRequiredData);

        const onsCmoSemanal = service.getOnsCmoSemanal(formGroup) as any;

        expect(onsCmoSemanal).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCmoSemanal should not enable id FormControl', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCmoSemanal should disable id FormControl', () => {
        const formGroup = service.createOnsCmoSemanalFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
