import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-cvu-usina-termicas.test-samples';

import { OnsCvuUsinaTermicasFormService } from './ons-cvu-usina-termicas-form.service';

describe('OnsCvuUsinaTermicas Form Service', () => {
  let service: OnsCvuUsinaTermicasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCvuUsinaTermicasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCvuUsinaTermicasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datIniciosemana: expect.any(Object),
            datFimsemana: expect.any(Object),
            anoReferencia: expect.any(Object),
            mesReferencia: expect.any(Object),
            numRevisao: expect.any(Object),
            nomSemanaoperativa: expect.any(Object),
            codModelos: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            nomUsina: expect.any(Object),
            valCvu: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCvuUsinaTermicas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datIniciosemana: expect.any(Object),
            datFimsemana: expect.any(Object),
            anoReferencia: expect.any(Object),
            mesReferencia: expect.any(Object),
            numRevisao: expect.any(Object),
            nomSemanaoperativa: expect.any(Object),
            codModelos: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            nomUsina: expect.any(Object),
            valCvu: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCvuUsinaTermicas', () => {
      it('should return NewOnsCvuUsinaTermicas for default OnsCvuUsinaTermicas initial value', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup(sampleWithNewData);

        const onsCvuUsinaTermicas = service.getOnsCvuUsinaTermicas(formGroup) as any;

        expect(onsCvuUsinaTermicas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCvuUsinaTermicas for empty OnsCvuUsinaTermicas initial value', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup();

        const onsCvuUsinaTermicas = service.getOnsCvuUsinaTermicas(formGroup) as any;

        expect(onsCvuUsinaTermicas).toMatchObject({});
      });

      it('should return IOnsCvuUsinaTermicas', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup(sampleWithRequiredData);

        const onsCvuUsinaTermicas = service.getOnsCvuUsinaTermicas(formGroup) as any;

        expect(onsCvuUsinaTermicas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCvuUsinaTermicas should not enable id FormControl', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCvuUsinaTermicas should disable id FormControl', () => {
        const formGroup = service.createOnsCvuUsinaTermicasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
