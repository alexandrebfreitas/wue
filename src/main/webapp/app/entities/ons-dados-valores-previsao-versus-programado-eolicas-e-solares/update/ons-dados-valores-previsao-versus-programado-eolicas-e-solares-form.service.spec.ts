import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.test-samples';

import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService } from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-form.service';

describe('OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares Form Service', () => {
  let service: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datProgramacao: expect.any(Object),
            numPatamar: expect.any(Object),
            codUsinapdp: expect.any(Object),
            nomUsinapdp: expect.any(Object),
            valPrevisao: expect.any(Object),
            valProgramado: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datProgramacao: expect.any(Object),
            numPatamar: expect.any(Object),
            codUsinapdp: expect.any(Object),
            nomUsinapdp: expect.any(Object),
            valPrevisao: expect.any(Object),
            valProgramado: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
      it('should return NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares for default OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares initial value', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup(sampleWithNewData);

        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = service.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
          formGroup,
        ) as any;

        expect(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares for empty OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares initial value', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup();

        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = service.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
          formGroup,
        ) as any;

        expect(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toMatchObject({});
      });

      it('should return IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup(sampleWithRequiredData);

        const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = service.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
          formGroup,
        ) as any;

        expect(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares should disable id FormControl', () => {
        const formGroup = service.createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
