import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente.test-samples';

import { OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormService } from './ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente-form.service';

describe('OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente Form Service', () => {
  let service: OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            numDesvioPermSobre: expect.any(Object),
            numDesvioPermSub: expect.any(Object),
            numDesvioDistSobre: expect.any(Object),
            numDesvioDistSub: expect.any(Object),
            numMinutos: expect.any(Object),
            numVioladodis: expect.any(Object),
            numVioladoperm: expect.any(Object),
            valDfp: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente should create a new form with FormGroup', () => {
        const formGroup =
          service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup(
            sampleWithRequiredData,
          );

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            numDesvioPermSobre: expect.any(Object),
            numDesvioPermSub: expect.any(Object),
            numDesvioDistSobre: expect.any(Object),
            numDesvioDistSub: expect.any(Object),
            numMinutos: expect.any(Object),
            numVioladodis: expect.any(Object),
            numVioladoperm: expect.any(Object),
            valDfp: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente', () => {
      it('should return NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente for default OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente initial value', () => {
        const formGroup =
          service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup(sampleWithNewData);

        const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
          service.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(formGroup) as any;

        expect(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente for empty OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente initial value', () => {
        const formGroup = service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup();

        const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
          service.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(formGroup) as any;

        expect(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente).toMatchObject({});
      });

      it('should return IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente', () => {
        const formGroup =
          service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup(
            sampleWithRequiredData,
          );

        const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
          service.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(formGroup) as any;

        expect(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente).toMatchObject(
          sampleWithRequiredData,
        );
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente should disable id FormControl', () => {
        const formGroup =
          service.createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteFormGroup(
            sampleWithRequiredData,
          );
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
