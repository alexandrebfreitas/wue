import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.test-samples';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService } from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-form.service';

describe('OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes Form Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valSm1: expect.any(Object),
            valSm2: expect.any(Object),
            valSm3: expect.any(Object),
            valSm4: expect.any(Object),
            valSm5: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valSm1: expect.any(Object),
            valSm2: expect.any(Object),
            valSm3: expect.any(Object),
            valSm4: expect.any(Object),
            valSm5: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes for default OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup(sampleWithNewData);

        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes for empty OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup();

        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes).toMatchObject({});
      });

      it('should return IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup(sampleWithRequiredData);

        const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes should not enable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes should disable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
