import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.test-samples';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-form.service';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas Form Service', () => {
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomUsina: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            dinInstante: expect.any(Object),
            valGeracao: expect.any(Object),
            valGeracaolimitada: expect.any(Object),
            valDisponibilidade: expect.any(Object),
            valGeracaoreferencia: expect.any(Object),
            valGeracaoreferenciafinal: expect.any(Object),
            codRazaorestricao: expect.any(Object),
            codOrigemrestricao: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomUsina: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            dinInstante: expect.any(Object),
            valGeracao: expect.any(Object),
            valGeracaolimitada: expect.any(Object),
            valDisponibilidade: expect.any(Object),
            valGeracaoreferencia: expect.any(Object),
            valGeracaoreferenciafinal: expect.any(Object),
            codRazaorestricao: expect.any(Object),
            codOrigemrestricao: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
      it('should return NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas for default OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas initial value', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup(sampleWithNewData);

        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
          formGroup,
        ) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas for empty OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas initial value', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup();

        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
          formGroup,
        ) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas).toMatchObject({});
      });

      it('should return IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup(sampleWithRequiredData);

        const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
          formGroup,
        ) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas should disable id FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
