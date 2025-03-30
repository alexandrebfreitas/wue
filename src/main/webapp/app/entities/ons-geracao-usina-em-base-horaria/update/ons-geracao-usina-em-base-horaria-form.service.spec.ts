import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-geracao-usina-em-base-horaria.test-samples';

import { OnsGeracaoUsinaEmBaseHorariaFormService } from './ons-geracao-usina-em-base-horaria-form.service';

describe('OnsGeracaoUsinaEmBaseHoraria Form Service', () => {
  let service: OnsGeracaoUsinaEmBaseHorariaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsGeracaoUsinaEmBaseHorariaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsGeracaoUsinaEmBaseHorariaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            codModalidadeoperacao: expect.any(Object),
            nomTipousina: expect.any(Object),
            nomTipocombustivel: expect.any(Object),
            nomUsina: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            valGeracao: expect.any(Object),
          }),
        );
      });

      it('passing IOnsGeracaoUsinaEmBaseHoraria should create a new form with FormGroup', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            codModalidadeoperacao: expect.any(Object),
            nomTipousina: expect.any(Object),
            nomTipocombustivel: expect.any(Object),
            nomUsina: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            valGeracao: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsGeracaoUsinaEmBaseHoraria', () => {
      it('should return NewOnsGeracaoUsinaEmBaseHoraria for default OnsGeracaoUsinaEmBaseHoraria initial value', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup(sampleWithNewData);

        const onsGeracaoUsinaEmBaseHoraria = service.getOnsGeracaoUsinaEmBaseHoraria(formGroup) as any;

        expect(onsGeracaoUsinaEmBaseHoraria).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsGeracaoUsinaEmBaseHoraria for empty OnsGeracaoUsinaEmBaseHoraria initial value', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup();

        const onsGeracaoUsinaEmBaseHoraria = service.getOnsGeracaoUsinaEmBaseHoraria(formGroup) as any;

        expect(onsGeracaoUsinaEmBaseHoraria).toMatchObject({});
      });

      it('should return IOnsGeracaoUsinaEmBaseHoraria', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup(sampleWithRequiredData);

        const onsGeracaoUsinaEmBaseHoraria = service.getOnsGeracaoUsinaEmBaseHoraria(formGroup) as any;

        expect(onsGeracaoUsinaEmBaseHoraria).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsGeracaoUsinaEmBaseHoraria should not enable id FormControl', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsGeracaoUsinaEmBaseHoraria should disable id FormControl', () => {
        const formGroup = service.createOnsGeracaoUsinaEmBaseHorariaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
