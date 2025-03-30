import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-programados-elementos-fluxo-controlado.test-samples';

import { OnsDadosProgramadosElementosFluxoControladoFormService } from './ons-dados-programados-elementos-fluxo-controlado-form.service';

describe('OnsDadosProgramadosElementosFluxoControlado Form Service', () => {
  let service: OnsDadosProgramadosElementosFluxoControladoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosProgramadosElementosFluxoControladoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosProgramadosElementosFluxoControladoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinProgramacaodia: expect.any(Object),
            numPatamar: expect.any(Object),
            nomElementofluxocontrolado: expect.any(Object),
            dscElementofluxocontrolado: expect.any(Object),
            tipTerminal: expect.any(Object),
            codSubmercado: expect.any(Object),
            valCarga: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosProgramadosElementosFluxoControlado should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinProgramacaodia: expect.any(Object),
            numPatamar: expect.any(Object),
            nomElementofluxocontrolado: expect.any(Object),
            dscElementofluxocontrolado: expect.any(Object),
            tipTerminal: expect.any(Object),
            codSubmercado: expect.any(Object),
            valCarga: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosProgramadosElementosFluxoControlado', () => {
      it('should return NewOnsDadosProgramadosElementosFluxoControlado for default OnsDadosProgramadosElementosFluxoControlado initial value', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup(sampleWithNewData);

        const onsDadosProgramadosElementosFluxoControlado = service.getOnsDadosProgramadosElementosFluxoControlado(formGroup) as any;

        expect(onsDadosProgramadosElementosFluxoControlado).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosProgramadosElementosFluxoControlado for empty OnsDadosProgramadosElementosFluxoControlado initial value', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup();

        const onsDadosProgramadosElementosFluxoControlado = service.getOnsDadosProgramadosElementosFluxoControlado(formGroup) as any;

        expect(onsDadosProgramadosElementosFluxoControlado).toMatchObject({});
      });

      it('should return IOnsDadosProgramadosElementosFluxoControlado', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup(sampleWithRequiredData);

        const onsDadosProgramadosElementosFluxoControlado = service.getOnsDadosProgramadosElementosFluxoControlado(formGroup) as any;

        expect(onsDadosProgramadosElementosFluxoControlado).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosProgramadosElementosFluxoControlado should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosProgramadosElementosFluxoControlado should disable id FormControl', () => {
        const formGroup = service.createOnsDadosProgramadosElementosFluxoControladoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
