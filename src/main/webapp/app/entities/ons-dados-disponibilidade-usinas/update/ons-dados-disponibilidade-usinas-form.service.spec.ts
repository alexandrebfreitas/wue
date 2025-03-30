import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-disponibilidade-usinas.test-samples';

import { OnsDadosDisponibilidadeUsinasFormService } from './ons-dados-disponibilidade-usinas-form.service';

describe('OnsDadosDisponibilidadeUsinas Form Service', () => {
  let service: OnsDadosDisponibilidadeUsinasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosDisponibilidadeUsinasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosDisponibilidadeUsinasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomUsina: expect.any(Object),
            idTipousina: expect.any(Object),
            nomTipocombustivel: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            dinInstante: expect.any(Object),
            valPotenciainstalada: expect.any(Object),
            valDispoperacional: expect.any(Object),
            valDispsincronizada: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosDisponibilidadeUsinas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomUsina: expect.any(Object),
            idTipousina: expect.any(Object),
            nomTipocombustivel: expect.any(Object),
            idOns: expect.any(Object),
            ceg: expect.any(Object),
            dinInstante: expect.any(Object),
            valPotenciainstalada: expect.any(Object),
            valDispoperacional: expect.any(Object),
            valDispsincronizada: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosDisponibilidadeUsinas', () => {
      it('should return NewOnsDadosDisponibilidadeUsinas for default OnsDadosDisponibilidadeUsinas initial value', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup(sampleWithNewData);

        const onsDadosDisponibilidadeUsinas = service.getOnsDadosDisponibilidadeUsinas(formGroup) as any;

        expect(onsDadosDisponibilidadeUsinas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosDisponibilidadeUsinas for empty OnsDadosDisponibilidadeUsinas initial value', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup();

        const onsDadosDisponibilidadeUsinas = service.getOnsDadosDisponibilidadeUsinas(formGroup) as any;

        expect(onsDadosDisponibilidadeUsinas).toMatchObject({});
      });

      it('should return IOnsDadosDisponibilidadeUsinas', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup(sampleWithRequiredData);

        const onsDadosDisponibilidadeUsinas = service.getOnsDadosDisponibilidadeUsinas(formGroup) as any;

        expect(onsDadosDisponibilidadeUsinas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosDisponibilidadeUsinas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosDisponibilidadeUsinas should disable id FormControl', () => {
        const formGroup = service.createOnsDadosDisponibilidadeUsinasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
