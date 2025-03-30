import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ena-diario-subsistema.test-samples';

import { OnsEnaDiarioSubsistemaFormService } from './ons-ena-diario-subsistema-form.service';

describe('OnsEnaDiarioSubsistema Form Service', () => {
  let service: OnsEnaDiarioSubsistemaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEnaDiarioSubsistemaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEnaDiarioSubsistemaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaArmazenavelRegiaoPercentualmlt: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEnaDiarioSubsistema should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaArmazenavelRegiaoPercentualmlt: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEnaDiarioSubsistema', () => {
      it('should return NewOnsEnaDiarioSubsistema for default OnsEnaDiarioSubsistema initial value', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup(sampleWithNewData);

        const onsEnaDiarioSubsistema = service.getOnsEnaDiarioSubsistema(formGroup) as any;

        expect(onsEnaDiarioSubsistema).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEnaDiarioSubsistema for empty OnsEnaDiarioSubsistema initial value', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup();

        const onsEnaDiarioSubsistema = service.getOnsEnaDiarioSubsistema(formGroup) as any;

        expect(onsEnaDiarioSubsistema).toMatchObject({});
      });

      it('should return IOnsEnaDiarioSubsistema', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup(sampleWithRequiredData);

        const onsEnaDiarioSubsistema = service.getOnsEnaDiarioSubsistema(formGroup) as any;

        expect(onsEnaDiarioSubsistema).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEnaDiarioSubsistema should not enable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEnaDiarioSubsistema should disable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioSubsistemaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
