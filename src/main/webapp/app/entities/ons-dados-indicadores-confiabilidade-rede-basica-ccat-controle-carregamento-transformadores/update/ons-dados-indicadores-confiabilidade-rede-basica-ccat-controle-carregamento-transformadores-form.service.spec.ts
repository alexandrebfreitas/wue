import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.test-samples';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService } from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-form.service';

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores Form Service', () => {
  let service: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codTipoagregacao: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            nomAgregacao: expect.any(Object),
            dinReferencia: expect.any(Object),
            numTransformadoresoperacao: expect.any(Object),
            numTransformadoresviolados: expect.any(Object),
            valCcat: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores should create a new form with FormGroup', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codTipoagregacao: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            nomAgregacao: expect.any(Object),
            dinReferencia: expect.any(Object),
            numTransformadoresoperacao: expect.any(Object),
            numTransformadoresviolados: expect.any(Object),
            valCcat: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
      it('should return NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores for default OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores initial value', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup(sampleWithNewData);

        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          service.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(formGroup) as any;

        expect(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores for empty OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores initial value', () => {
        const formGroup = service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup();

        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          service.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(formGroup) as any;

        expect(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toMatchObject({});
      });

      it('should return IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup(sampleWithRequiredData);

        const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
          service.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(formGroup) as any;

        expect(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores should disable id FormControl', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
