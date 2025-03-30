import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.test-samples';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService } from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-form.service';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas Form Service', () => {
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup();

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

      it('passing IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup(sampleWithRequiredData);

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

    describe('getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
      it('should return NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas for default OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas initial value', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup(sampleWithNewData);

        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(formGroup) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas for empty OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas initial value', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup();

        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(formGroup) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toMatchObject({});
      });

      it('should return IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup(sampleWithRequiredData);

        const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
          service.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(formGroup) as any;

        expect(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas should disable id FormControl', () => {
        const formGroup = service.createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
