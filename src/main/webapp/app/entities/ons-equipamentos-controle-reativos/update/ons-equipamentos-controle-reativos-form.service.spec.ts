import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-equipamentos-controle-reativos.test-samples';

import { OnsEquipamentosControleReativosFormService } from './ons-equipamentos-controle-reativos-form.service';

describe('OnsEquipamentosControleReativos Form Service', () => {
  let service: OnsEquipamentosControleReativosFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEquipamentosControleReativosFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEquipamentosControleReativosFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomSubestacao: expect.any(Object),
            nomAgenteProprietario: expect.any(Object),
            nomTipoderede: expect.any(Object),
            nomTipoequipamento: expect.any(Object),
            nomEquipamento: expect.any(Object),
            valPotreativanominalMvar: expect.any(Object),
            valLimiteinferiorMvar: expect.any(Object),
            valLimitesuperiorMvar: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            codEquipamento: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEquipamentosControleReativos should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomSubestacao: expect.any(Object),
            nomAgenteProprietario: expect.any(Object),
            nomTipoderede: expect.any(Object),
            nomTipoequipamento: expect.any(Object),
            nomEquipamento: expect.any(Object),
            valPotreativanominalMvar: expect.any(Object),
            valLimiteinferiorMvar: expect.any(Object),
            valLimitesuperiorMvar: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            codEquipamento: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEquipamentosControleReativos', () => {
      it('should return NewOnsEquipamentosControleReativos for default OnsEquipamentosControleReativos initial value', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup(sampleWithNewData);

        const onsEquipamentosControleReativos = service.getOnsEquipamentosControleReativos(formGroup) as any;

        expect(onsEquipamentosControleReativos).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEquipamentosControleReativos for empty OnsEquipamentosControleReativos initial value', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup();

        const onsEquipamentosControleReativos = service.getOnsEquipamentosControleReativos(formGroup) as any;

        expect(onsEquipamentosControleReativos).toMatchObject({});
      });

      it('should return IOnsEquipamentosControleReativos', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup(sampleWithRequiredData);

        const onsEquipamentosControleReativos = service.getOnsEquipamentosControleReativos(formGroup) as any;

        expect(onsEquipamentosControleReativos).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEquipamentosControleReativos should not enable id FormControl', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEquipamentosControleReativos should disable id FormControl', () => {
        const formGroup = service.createOnsEquipamentosControleReativosFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
