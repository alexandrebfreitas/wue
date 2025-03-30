import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-capacidade-instalada-geracao.test-samples';

import { OnsCapacidadeInstaladaGeracaoFormService } from './ons-capacidade-instalada-geracao-form.service';

describe('OnsCapacidadeInstaladaGeracao Form Service', () => {
  let service: OnsCapacidadeInstaladaGeracaoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCapacidadeInstaladaGeracaoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCapacidadeInstaladaGeracaoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            nomTipousina: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            nomUnidadegeradora: expect.any(Object),
            codEquipamento: expect.any(Object),
            numUnidadegeradora: expect.any(Object),
            nomCombustivel: expect.any(Object),
            datEntradateste: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            valPotenciaefetiva: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCapacidadeInstaladaGeracao should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            nomTipousina: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            nomUnidadegeradora: expect.any(Object),
            codEquipamento: expect.any(Object),
            numUnidadegeradora: expect.any(Object),
            nomCombustivel: expect.any(Object),
            datEntradateste: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            valPotenciaefetiva: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCapacidadeInstaladaGeracao', () => {
      it('should return NewOnsCapacidadeInstaladaGeracao for default OnsCapacidadeInstaladaGeracao initial value', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup(sampleWithNewData);

        const onsCapacidadeInstaladaGeracao = service.getOnsCapacidadeInstaladaGeracao(formGroup) as any;

        expect(onsCapacidadeInstaladaGeracao).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCapacidadeInstaladaGeracao for empty OnsCapacidadeInstaladaGeracao initial value', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup();

        const onsCapacidadeInstaladaGeracao = service.getOnsCapacidadeInstaladaGeracao(formGroup) as any;

        expect(onsCapacidadeInstaladaGeracao).toMatchObject({});
      });

      it('should return IOnsCapacidadeInstaladaGeracao', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup(sampleWithRequiredData);

        const onsCapacidadeInstaladaGeracao = service.getOnsCapacidadeInstaladaGeracao(formGroup) as any;

        expect(onsCapacidadeInstaladaGeracao).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCapacidadeInstaladaGeracao should not enable id FormControl', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCapacidadeInstaladaGeracao should disable id FormControl', () => {
        const formGroup = service.createOnsCapacidadeInstaladaGeracaoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
