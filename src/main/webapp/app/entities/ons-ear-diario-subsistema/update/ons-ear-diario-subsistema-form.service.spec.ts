import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ear-diario-subsistema.test-samples';

import { OnsEarDiarioSubsistemaFormService } from './ons-ear-diario-subsistema-form.service';

describe('OnsEarDiarioSubsistema Form Service', () => {
  let service: OnsEarDiarioSubsistemaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEarDiarioSubsistemaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEarDiarioSubsistemaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            earData: expect.any(Object),
            earMaxSubsistema: expect.any(Object),
            earVerifSubsistemaMwmes: expect.any(Object),
            earVerifSubsistemaPercentual: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEarDiarioSubsistema should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            earData: expect.any(Object),
            earMaxSubsistema: expect.any(Object),
            earVerifSubsistemaMwmes: expect.any(Object),
            earVerifSubsistemaPercentual: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEarDiarioSubsistema', () => {
      it('should return NewOnsEarDiarioSubsistema for default OnsEarDiarioSubsistema initial value', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup(sampleWithNewData);

        const onsEarDiarioSubsistema = service.getOnsEarDiarioSubsistema(formGroup) as any;

        expect(onsEarDiarioSubsistema).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEarDiarioSubsistema for empty OnsEarDiarioSubsistema initial value', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup();

        const onsEarDiarioSubsistema = service.getOnsEarDiarioSubsistema(formGroup) as any;

        expect(onsEarDiarioSubsistema).toMatchObject({});
      });

      it('should return IOnsEarDiarioSubsistema', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup(sampleWithRequiredData);

        const onsEarDiarioSubsistema = service.getOnsEarDiarioSubsistema(formGroup) as any;

        expect(onsEarDiarioSubsistema).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEarDiarioSubsistema should not enable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEarDiarioSubsistema should disable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioSubsistemaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
