import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-balanco-energia-dessem.test-samples';

import { OnsBalancoEnergiaDessemFormService } from './ons-balanco-energia-dessem-form.service';

describe('OnsBalancoEnergiaDessem Form Service', () => {
  let service: OnsBalancoEnergiaDessemFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsBalancoEnergiaDessemFormService);
  });

  describe('Service methods', () => {
    describe('createOnsBalancoEnergiaDessemFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valDemanda: expect.any(Object),
            valGeracaohidraulicamwmed: expect.any(Object),
            valGeracaopchmwmed: expect.any(Object),
            valGeracaotermicamwed: expect.any(Object),
            valGeracaopctmwmed: expect.any(Object),
            valGeracaoeolicamwmed: expect.any(Object),
            valGeracaofotovoltaicamwmed: expect.any(Object),
          }),
        );
      });

      it('passing IOnsBalancoEnergiaDessem should create a new form with FormGroup', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valDemanda: expect.any(Object),
            valGeracaohidraulicamwmed: expect.any(Object),
            valGeracaopchmwmed: expect.any(Object),
            valGeracaotermicamwed: expect.any(Object),
            valGeracaopctmwmed: expect.any(Object),
            valGeracaoeolicamwmed: expect.any(Object),
            valGeracaofotovoltaicamwmed: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsBalancoEnergiaDessem', () => {
      it('should return NewOnsBalancoEnergiaDessem for default OnsBalancoEnergiaDessem initial value', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup(sampleWithNewData);

        const onsBalancoEnergiaDessem = service.getOnsBalancoEnergiaDessem(formGroup) as any;

        expect(onsBalancoEnergiaDessem).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsBalancoEnergiaDessem for empty OnsBalancoEnergiaDessem initial value', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup();

        const onsBalancoEnergiaDessem = service.getOnsBalancoEnergiaDessem(formGroup) as any;

        expect(onsBalancoEnergiaDessem).toMatchObject({});
      });

      it('should return IOnsBalancoEnergiaDessem', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup(sampleWithRequiredData);

        const onsBalancoEnergiaDessem = service.getOnsBalancoEnergiaDessem(formGroup) as any;

        expect(onsBalancoEnergiaDessem).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsBalancoEnergiaDessem should not enable id FormControl', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsBalancoEnergiaDessem should disable id FormControl', () => {
        const formGroup = service.createOnsBalancoEnergiaDessemFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
