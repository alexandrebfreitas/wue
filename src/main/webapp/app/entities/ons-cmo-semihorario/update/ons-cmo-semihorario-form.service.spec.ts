import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-cmo-semihorario.test-samples';

import { OnsCmoSemihorarioFormService } from './ons-cmo-semihorario-form.service';

describe('OnsCmoSemihorario Form Service', () => {
  let service: OnsCmoSemihorarioFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCmoSemihorarioFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCmoSemihorarioFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCmo: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCmoSemihorario should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCmo: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCmoSemihorario', () => {
      it('should return NewOnsCmoSemihorario for default OnsCmoSemihorario initial value', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup(sampleWithNewData);

        const onsCmoSemihorario = service.getOnsCmoSemihorario(formGroup) as any;

        expect(onsCmoSemihorario).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCmoSemihorario for empty OnsCmoSemihorario initial value', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup();

        const onsCmoSemihorario = service.getOnsCmoSemihorario(formGroup) as any;

        expect(onsCmoSemihorario).toMatchObject({});
      });

      it('should return IOnsCmoSemihorario', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup(sampleWithRequiredData);

        const onsCmoSemihorario = service.getOnsCmoSemihorario(formGroup) as any;

        expect(onsCmoSemihorario).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCmoSemihorario should not enable id FormControl', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCmoSemihorario should disable id FormControl', () => {
        const formGroup = service.createOnsCmoSemihorarioFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
