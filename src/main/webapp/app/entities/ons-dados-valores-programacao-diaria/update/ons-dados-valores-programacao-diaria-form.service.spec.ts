import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-valores-programacao-diaria.test-samples';

import { OnsDadosValoresProgramacaoDiariaFormService } from './ons-dados-valores-programacao-diaria-form.service';

describe('OnsDadosValoresProgramacaoDiaria Form Service', () => {
  let service: OnsDadosValoresProgramacaoDiariaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosValoresProgramacaoDiariaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosValoresProgramacaoDiariaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinProgramacaodia: expect.any(Object),
            numPatamar: expect.any(Object),
            codExibicaousina: expect.any(Object),
            nomUsina: expect.any(Object),
            tipGeracao: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            valGeracaoprogramada: expect.any(Object),
            valDisponibilidade: expect.any(Object),
            valOrdemmerito: expect.any(Object),
            valInflexibilidade: expect.any(Object),
            valUc: expect.any(Object),
            valRazaoeletrica: expect.any(Object),
            valGeracaoenergetica: expect.any(Object),
            valGesubgsub: expect.any(Object),
            valExportacao: expect.any(Object),
            valReposicaoexportacao: expect.any(Object),
            valFaltacombustivel: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosValoresProgramacaoDiaria should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinProgramacaodia: expect.any(Object),
            numPatamar: expect.any(Object),
            codExibicaousina: expect.any(Object),
            nomUsina: expect.any(Object),
            tipGeracao: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            valGeracaoprogramada: expect.any(Object),
            valDisponibilidade: expect.any(Object),
            valOrdemmerito: expect.any(Object),
            valInflexibilidade: expect.any(Object),
            valUc: expect.any(Object),
            valRazaoeletrica: expect.any(Object),
            valGeracaoenergetica: expect.any(Object),
            valGesubgsub: expect.any(Object),
            valExportacao: expect.any(Object),
            valReposicaoexportacao: expect.any(Object),
            valFaltacombustivel: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosValoresProgramacaoDiaria', () => {
      it('should return NewOnsDadosValoresProgramacaoDiaria for default OnsDadosValoresProgramacaoDiaria initial value', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup(sampleWithNewData);

        const onsDadosValoresProgramacaoDiaria = service.getOnsDadosValoresProgramacaoDiaria(formGroup) as any;

        expect(onsDadosValoresProgramacaoDiaria).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosValoresProgramacaoDiaria for empty OnsDadosValoresProgramacaoDiaria initial value', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup();

        const onsDadosValoresProgramacaoDiaria = service.getOnsDadosValoresProgramacaoDiaria(formGroup) as any;

        expect(onsDadosValoresProgramacaoDiaria).toMatchObject({});
      });

      it('should return IOnsDadosValoresProgramacaoDiaria', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup(sampleWithRequiredData);

        const onsDadosValoresProgramacaoDiaria = service.getOnsDadosValoresProgramacaoDiaria(formGroup) as any;

        expect(onsDadosValoresProgramacaoDiaria).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosValoresProgramacaoDiaria should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosValoresProgramacaoDiaria should disable id FormControl', () => {
        const formGroup = service.createOnsDadosValoresProgramacaoDiariaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
