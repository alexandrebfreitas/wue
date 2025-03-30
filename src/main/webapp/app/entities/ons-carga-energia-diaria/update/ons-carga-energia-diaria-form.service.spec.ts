import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-carga-energia-diaria.test-samples';

import { OnsCargaEnergiaDiariaFormService } from './ons-carga-energia-diaria-form.service';

describe('OnsCargaEnergiaDiaria Form Service', () => {
  let service: OnsCargaEnergiaDiariaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCargaEnergiaDiariaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCargaEnergiaDiariaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup();

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

      it('passing IOnsCargaEnergiaDiaria should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup(sampleWithRequiredData);

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

    describe('getOnsCargaEnergiaDiaria', () => {
      it('should return NewOnsCargaEnergiaDiaria for default OnsCargaEnergiaDiaria initial value', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup(sampleWithNewData);

        const onsCargaEnergiaDiaria = service.getOnsCargaEnergiaDiaria(formGroup) as any;

        expect(onsCargaEnergiaDiaria).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCargaEnergiaDiaria for empty OnsCargaEnergiaDiaria initial value', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup();

        const onsCargaEnergiaDiaria = service.getOnsCargaEnergiaDiaria(formGroup) as any;

        expect(onsCargaEnergiaDiaria).toMatchObject({});
      });

      it('should return IOnsCargaEnergiaDiaria', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup(sampleWithRequiredData);

        const onsCargaEnergiaDiaria = service.getOnsCargaEnergiaDiaria(formGroup) as any;

        expect(onsCargaEnergiaDiaria).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCargaEnergiaDiaria should not enable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCargaEnergiaDiaria should disable id FormControl', () => {
        const formGroup = service.createOnsCargaEnergiaDiariaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
