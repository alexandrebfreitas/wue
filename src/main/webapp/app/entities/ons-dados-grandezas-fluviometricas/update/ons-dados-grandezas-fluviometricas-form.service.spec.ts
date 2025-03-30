import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-grandezas-fluviometricas.test-samples';

import { OnsDadosGrandezasFluviometricasFormService } from './ons-dados-grandezas-fluviometricas-form.service';

describe('OnsDadosGrandezasFluviometricas Form Service', () => {
  let service: OnsDadosGrandezasFluviometricasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosGrandezasFluviometricasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosGrandezasFluviometricasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idPostofluv: expect.any(Object),
            nomPostofluviometrico: expect.any(Object),
            valLatitude: expect.any(Object),
            valLongitude: expect.any(Object),
            nomRio: expect.any(Object),
            nomBacia: expect.any(Object),
            dinMedicao: expect.any(Object),
            valVazaomedia: expect.any(Object),
            valVazaomediaincr: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosGrandezasFluviometricas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idPostofluv: expect.any(Object),
            nomPostofluviometrico: expect.any(Object),
            valLatitude: expect.any(Object),
            valLongitude: expect.any(Object),
            nomRio: expect.any(Object),
            nomBacia: expect.any(Object),
            dinMedicao: expect.any(Object),
            valVazaomedia: expect.any(Object),
            valVazaomediaincr: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosGrandezasFluviometricas', () => {
      it('should return NewOnsDadosGrandezasFluviometricas for default OnsDadosGrandezasFluviometricas initial value', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup(sampleWithNewData);

        const onsDadosGrandezasFluviometricas = service.getOnsDadosGrandezasFluviometricas(formGroup) as any;

        expect(onsDadosGrandezasFluviometricas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosGrandezasFluviometricas for empty OnsDadosGrandezasFluviometricas initial value', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup();

        const onsDadosGrandezasFluviometricas = service.getOnsDadosGrandezasFluviometricas(formGroup) as any;

        expect(onsDadosGrandezasFluviometricas).toMatchObject({});
      });

      it('should return IOnsDadosGrandezasFluviometricas', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup(sampleWithRequiredData);

        const onsDadosGrandezasFluviometricas = service.getOnsDadosGrandezasFluviometricas(formGroup) as any;

        expect(onsDadosGrandezasFluviometricas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosGrandezasFluviometricas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosGrandezasFluviometricas should disable id FormControl', () => {
        const formGroup = service.createOnsDadosGrandezasFluviometricasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
