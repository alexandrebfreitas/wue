import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-taxas-teifa-e-teip.test-samples';

import { OnsTaxasTeifaETeipFormService } from './ons-taxas-teifa-e-teip-form.service';

describe('OnsTaxasTeifaETeip Form Service', () => {
  let service: OnsTaxasTeifaETeipFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsTaxasTeifaETeipFormService);
  });

  describe('Service methods', () => {
    describe('createOnsTaxasTeifaETeipFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomUsina: expect.any(Object),
            codCeg: expect.any(Object),
            idTipousina: expect.any(Object),
            dinMes: expect.any(Object),
            nomTaxa: expect.any(Object),
            valTaxa: expect.any(Object),
            numVersao: expect.any(Object),
            dinInstante: expect.any(Object),
          }),
        );
      });

      it('passing IOnsTaxasTeifaETeip should create a new form with FormGroup', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomUsina: expect.any(Object),
            codCeg: expect.any(Object),
            idTipousina: expect.any(Object),
            dinMes: expect.any(Object),
            nomTaxa: expect.any(Object),
            valTaxa: expect.any(Object),
            numVersao: expect.any(Object),
            dinInstante: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsTaxasTeifaETeip', () => {
      it('should return NewOnsTaxasTeifaETeip for default OnsTaxasTeifaETeip initial value', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup(sampleWithNewData);

        const onsTaxasTeifaETeip = service.getOnsTaxasTeifaETeip(formGroup) as any;

        expect(onsTaxasTeifaETeip).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsTaxasTeifaETeip for empty OnsTaxasTeifaETeip initial value', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup();

        const onsTaxasTeifaETeip = service.getOnsTaxasTeifaETeip(formGroup) as any;

        expect(onsTaxasTeifaETeip).toMatchObject({});
      });

      it('should return IOnsTaxasTeifaETeip', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup(sampleWithRequiredData);

        const onsTaxasTeifaETeip = service.getOnsTaxasTeifaETeip(formGroup) as any;

        expect(onsTaxasTeifaETeip).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsTaxasTeifaETeip should not enable id FormControl', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsTaxasTeifaETeip should disable id FormControl', () => {
        const formGroup = service.createOnsTaxasTeifaETeipFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
