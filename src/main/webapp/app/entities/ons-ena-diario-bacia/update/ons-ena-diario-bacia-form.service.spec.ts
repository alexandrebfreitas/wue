import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ena-diario-bacia.test-samples';

import { OnsEnaDiarioBaciaFormService } from './ons-ena-diario-bacia-form.service';

describe('OnsEnaDiarioBacia Form Service', () => {
  let service: OnsEnaDiarioBaciaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEnaDiarioBaciaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEnaDiarioBaciaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaArmazenavelBaciaPercentualmlt: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEnaDiarioBacia should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaArmazenavelBaciaPercentualmlt: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEnaDiarioBacia', () => {
      it('should return NewOnsEnaDiarioBacia for default OnsEnaDiarioBacia initial value', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup(sampleWithNewData);

        const onsEnaDiarioBacia = service.getOnsEnaDiarioBacia(formGroup) as any;

        expect(onsEnaDiarioBacia).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEnaDiarioBacia for empty OnsEnaDiarioBacia initial value', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup();

        const onsEnaDiarioBacia = service.getOnsEnaDiarioBacia(formGroup) as any;

        expect(onsEnaDiarioBacia).toMatchObject({});
      });

      it('should return IOnsEnaDiarioBacia', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup(sampleWithRequiredData);

        const onsEnaDiarioBacia = service.getOnsEnaDiarioBacia(formGroup) as any;

        expect(onsEnaDiarioBacia).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEnaDiarioBacia should not enable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEnaDiarioBacia should disable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioBaciaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
