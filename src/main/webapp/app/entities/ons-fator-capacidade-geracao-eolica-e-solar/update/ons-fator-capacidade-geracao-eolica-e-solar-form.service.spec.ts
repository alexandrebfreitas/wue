import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-fator-capacidade-geracao-eolica-e-solar.test-samples';

import { OnsFatorCapacidadeGeracaoEolicaESolarFormService } from './ons-fator-capacidade-geracao-eolica-e-solar-form.service';

describe('OnsFatorCapacidadeGeracaoEolicaESolar Form Service', () => {
  let service: OnsFatorCapacidadeGeracaoEolicaESolarFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsFatorCapacidadeGeracaoEolicaESolarFormService);
  });

  describe('Service methods', () => {
    describe('createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            codPontoconexao: expect.any(Object),
            nomPontoconexao: expect.any(Object),
            nomLocalizacao: expect.any(Object),
            valLatitudesecoletora: expect.any(Object),
            valLongitudesecoletora: expect.any(Object),
            valLatitudepontoconexao: expect.any(Object),
            valLongitudepontoconexao: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            nomTipousina: expect.any(Object),
            nomUsinaConjunto: expect.any(Object),
            dinInstante: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            valGeracaoprogramada: expect.any(Object),
            valGeracaoverificada: expect.any(Object),
            valCapacidadeinstalada: expect.any(Object),
            valFatorcapacidade: expect.any(Object),
          }),
        );
      });

      it('passing IOnsFatorCapacidadeGeracaoEolicaESolar should create a new form with FormGroup', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            codPontoconexao: expect.any(Object),
            nomPontoconexao: expect.any(Object),
            nomLocalizacao: expect.any(Object),
            valLatitudesecoletora: expect.any(Object),
            valLongitudesecoletora: expect.any(Object),
            valLatitudepontoconexao: expect.any(Object),
            valLongitudepontoconexao: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            nomTipousina: expect.any(Object),
            nomUsinaConjunto: expect.any(Object),
            dinInstante: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            valGeracaoprogramada: expect.any(Object),
            valGeracaoverificada: expect.any(Object),
            valCapacidadeinstalada: expect.any(Object),
            valFatorcapacidade: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsFatorCapacidadeGeracaoEolicaESolar', () => {
      it('should return NewOnsFatorCapacidadeGeracaoEolicaESolar for default OnsFatorCapacidadeGeracaoEolicaESolar initial value', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup(sampleWithNewData);

        const onsFatorCapacidadeGeracaoEolicaESolar = service.getOnsFatorCapacidadeGeracaoEolicaESolar(formGroup) as any;

        expect(onsFatorCapacidadeGeracaoEolicaESolar).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsFatorCapacidadeGeracaoEolicaESolar for empty OnsFatorCapacidadeGeracaoEolicaESolar initial value', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup();

        const onsFatorCapacidadeGeracaoEolicaESolar = service.getOnsFatorCapacidadeGeracaoEolicaESolar(formGroup) as any;

        expect(onsFatorCapacidadeGeracaoEolicaESolar).toMatchObject({});
      });

      it('should return IOnsFatorCapacidadeGeracaoEolicaESolar', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup(sampleWithRequiredData);

        const onsFatorCapacidadeGeracaoEolicaESolar = service.getOnsFatorCapacidadeGeracaoEolicaESolar(formGroup) as any;

        expect(onsFatorCapacidadeGeracaoEolicaESolar).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsFatorCapacidadeGeracaoEolicaESolar should not enable id FormControl', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsFatorCapacidadeGeracaoEolicaESolar should disable id FormControl', () => {
        const formGroup = service.createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
