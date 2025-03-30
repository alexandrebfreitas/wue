import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ear-diario-bacia.test-samples';

import { OnsEarDiarioBaciaFormService } from './ons-ear-diario-bacia-form.service';

describe('OnsEarDiarioBacia Form Service', () => {
  let service: OnsEarDiarioBaciaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEarDiarioBaciaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEarDiarioBaciaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomCurto: expect.any(Object),
            earData: expect.any(Object),
            earMaxBacia: expect.any(Object),
            earVerifBaciaMwmes: expect.any(Object),
            earVerifBaciaPercentual: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEarDiarioBacia should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomCurto: expect.any(Object),
            earData: expect.any(Object),
            earMaxBacia: expect.any(Object),
            earVerifBaciaMwmes: expect.any(Object),
            earVerifBaciaPercentual: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEarDiarioBacia', () => {
      it('should return NewOnsEarDiarioBacia for default OnsEarDiarioBacia initial value', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup(sampleWithNewData);

        const onsEarDiarioBacia = service.getOnsEarDiarioBacia(formGroup) as any;

        expect(onsEarDiarioBacia).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEarDiarioBacia for empty OnsEarDiarioBacia initial value', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup();

        const onsEarDiarioBacia = service.getOnsEarDiarioBacia(formGroup) as any;

        expect(onsEarDiarioBacia).toMatchObject({});
      });

      it('should return IOnsEarDiarioBacia', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup(sampleWithRequiredData);

        const onsEarDiarioBacia = service.getOnsEarDiarioBacia(formGroup) as any;

        expect(onsEarDiarioBacia).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEarDiarioBacia should not enable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEarDiarioBacia should disable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioBaciaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
