import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.test-samples';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-form.service';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal Form Service', () => {
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup();

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
            valDispf: expect.any(Object),
            valIndisppf: expect.any(Object),
            valIndispff: expect.any(Object),
            valDmdff: expect.any(Object),
            valFdff: expect.any(Object),
            valTdff: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup(sampleWithRequiredData);

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

    describe('getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
      it('should return NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal for default OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal initial value', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup(sampleWithNewData);

        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(formGroup) as any;

        expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal for empty OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal initial value', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup();

        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(formGroup) as any;

        expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toMatchObject({});
      });

      it('should return IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup(sampleWithRequiredData);

        const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
          service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(formGroup) as any;

        expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal should not enable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal should disable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
