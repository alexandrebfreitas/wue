import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.test-samples';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-form.service';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual Form Service', () => {
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            idTipousina: expect.any(Object),
            idUsina: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            codEquipamento: expect.any(Object),
            numUnidadegeradora: expect.any(Object),
            nomUnidadegeradora: expect.any(Object),
            valPotencia: expect.any(Object),
            dinAno: expect.any(Object),
            valDispf: expect.any(Object),
            valIndisppf: expect.any(Object),
            valIndispff: expect.any(Object),
            valDmdff: expect.any(Object),
            valFdff: expect.any(Object),
            valTdff: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            idTipousina: expect.any(Object),
            idUsina: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            codEquipamento: expect.any(Object),
            numUnidadegeradora: expect.any(Object),
            nomUnidadegeradora: expect.any(Object),
            valPotencia: expect.any(Object),
            dinAno: expect.any(Object),
            valDispf: expect.any(Object),
            valIndisppf: expect.any(Object),
            valIndispff: expect.any(Object),
            valDmdff: expect.any(Object),
            valFdff: expect.any(Object),
            valTdff: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
      it('should return NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual for default OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual initial value', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup(sampleWithNewData);

        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(formGroup) as any;

        expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual for empty OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual initial value', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup();

        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(formGroup) as any;

        expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toMatchObject({});
      });

      it('should return IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup(sampleWithRequiredData);

        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
          service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(formGroup) as any;

        expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual should not enable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual should disable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
