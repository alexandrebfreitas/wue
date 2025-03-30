import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-hidrologicos-volume-espera-recomendado.test-samples';

import { OnsDadosHidrologicosVolumeEsperaRecomendadoFormService } from './ons-dados-hidrologicos-volume-espera-recomendado-form.service';

describe('OnsDadosHidrologicosVolumeEsperaRecomendado Form Service', () => {
  let service: OnsDadosHidrologicosVolumeEsperaRecomendadoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosHidrologicosVolumeEsperaRecomendadoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            valVolumeespera: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosHidrologicosVolumeEsperaRecomendado should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            valVolumeespera: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosHidrologicosVolumeEsperaRecomendado', () => {
      it('should return NewOnsDadosHidrologicosVolumeEsperaRecomendado for default OnsDadosHidrologicosVolumeEsperaRecomendado initial value', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup(sampleWithNewData);

        const onsDadosHidrologicosVolumeEsperaRecomendado = service.getOnsDadosHidrologicosVolumeEsperaRecomendado(formGroup) as any;

        expect(onsDadosHidrologicosVolumeEsperaRecomendado).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosHidrologicosVolumeEsperaRecomendado for empty OnsDadosHidrologicosVolumeEsperaRecomendado initial value', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup();

        const onsDadosHidrologicosVolumeEsperaRecomendado = service.getOnsDadosHidrologicosVolumeEsperaRecomendado(formGroup) as any;

        expect(onsDadosHidrologicosVolumeEsperaRecomendado).toMatchObject({});
      });

      it('should return IOnsDadosHidrologicosVolumeEsperaRecomendado', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup(sampleWithRequiredData);

        const onsDadosHidrologicosVolumeEsperaRecomendado = service.getOnsDadosHidrologicosVolumeEsperaRecomendado(formGroup) as any;

        expect(onsDadosHidrologicosVolumeEsperaRecomendado).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosHidrologicosVolumeEsperaRecomendado should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosHidrologicosVolumeEsperaRecomendado should disable id FormControl', () => {
        const formGroup = service.createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
