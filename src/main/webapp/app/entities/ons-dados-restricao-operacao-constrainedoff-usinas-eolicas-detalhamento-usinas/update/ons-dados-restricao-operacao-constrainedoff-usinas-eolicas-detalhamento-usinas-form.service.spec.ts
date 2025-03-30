import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.test-samples';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-form.service';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas Form Service', () => {
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            idEstado: expect.any(Object),
            nomConjuntousina: expect.any(Object),
            nomUsina: expect.any(Object),
            dinInstante: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            valVentoverificado: expect.any(Object),
            flgDadoventoinvalido: expect.any(Object),
            valGeracaoestimada: expect.any(Object),
            valGeracaoverificada: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas should create a new form with FormGroup', () => {
        const formGroup =
          service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            idEstado: expect.any(Object),
            nomConjuntousina: expect.any(Object),
            nomUsina: expect.any(Object),
            dinInstante: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            valVentoverificado: expect.any(Object),
            flgDadoventoinvalido: expect.any(Object),
            valGeracaoestimada: expect.any(Object),
            valGeracaoverificada: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
      it('should return NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas for default OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas initial value', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup(sampleWithNewData);

        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(formGroup) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas for empty OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas initial value', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup();

        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(formGroup) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toMatchObject({});
      });

      it('should return IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas', () => {
        const formGroup =
          service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup(sampleWithRequiredData);

        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
          service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(formGroup) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas should disable id FormControl', () => {
        const formGroup =
          service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
