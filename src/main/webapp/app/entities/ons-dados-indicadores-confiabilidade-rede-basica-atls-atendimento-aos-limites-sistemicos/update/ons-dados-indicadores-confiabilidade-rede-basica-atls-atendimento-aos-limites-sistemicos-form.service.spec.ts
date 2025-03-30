import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.test-samples';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService } from './ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-form.service';

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos Form Service', () => {
  let service: OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomFluxo: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valAtls: expect.any(Object),
            numHorasviolacao: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos should create a new form with FormGroup', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomFluxo: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valAtls: expect.any(Object),
            numHorasviolacao: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos', () => {
      it('should return NewOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos for default OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos initial value', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup(sampleWithNewData);

        const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos =
          service.getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(formGroup) as any;

        expect(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos for empty OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos initial value', () => {
        const formGroup = service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup();

        const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos =
          service.getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(formGroup) as any;

        expect(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos).toMatchObject({});
      });

      it('should return IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup(sampleWithRequiredData);

        const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos =
          service.getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(formGroup) as any;

        expect(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos should disable id FormControl', () => {
        const formGroup =
          service.createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
