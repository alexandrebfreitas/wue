import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.test-samples';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService } from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-form.service';

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes Form Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valCiper1: expect.any(Object),
            valCiper2: expect.any(Object),
            valCiper3: expect.any(Object),
            valCiper4: expect.any(Object),
            valCiper5: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes should create a new form with FormGroup', () => {
        const formGroup =
          service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valCiper1: expect.any(Object),
            valCiper2: expect.any(Object),
            valCiper3: expect.any(Object),
            valCiper4: expect.any(Object),
            valCiper5: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes for default OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes initial value', () => {
        const formGroup =
          service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup(sampleWithNewData);

        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes for empty OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup();

        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toMatchObject({});
      });

      it('should return IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes', () => {
        const formGroup =
          service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup(sampleWithRequiredData);

        const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes should not enable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes should disable id FormControl', () => {
        const formGroup =
          service.createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
