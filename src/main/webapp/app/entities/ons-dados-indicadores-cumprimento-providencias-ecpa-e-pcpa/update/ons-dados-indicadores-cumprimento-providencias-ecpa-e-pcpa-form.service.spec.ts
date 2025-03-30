import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.test-samples';

import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService } from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-form.service';

describe('OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa Form Service', () => {
  let service: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            dinReferencia: expect.any(Object),
            numNprcConcluidas: expect.any(Object),
            numNprpProgramadas: expect.any(Object),
            numNpratAtrasadas: expect.any(Object),
            numNpraAntecipadas: expect.any(Object),
            numNprcpConcluidasPrazo: expect.any(Object),
            valEcpa: expect.any(Object),
            valPcpa: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            dinReferencia: expect.any(Object),
            numNprcConcluidas: expect.any(Object),
            numNprpProgramadas: expect.any(Object),
            numNpratAtrasadas: expect.any(Object),
            numNpraAntecipadas: expect.any(Object),
            numNprcpConcluidasPrazo: expect.any(Object),
            valEcpa: expect.any(Object),
            valPcpa: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
      it('should return NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa for default OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa initial value', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup(sampleWithNewData);

        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = service.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
          formGroup,
        ) as any;

        expect(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa for empty OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa initial value', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup();

        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = service.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
          formGroup,
        ) as any;

        expect(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toMatchObject({});
      });

      it('should return IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup(sampleWithRequiredData);

        const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = service.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
          formGroup,
        ) as any;

        expect(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa should disable id FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
