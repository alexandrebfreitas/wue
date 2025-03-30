import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.test-samples';

import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService } from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-form.service';

describe('OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas Form Service', () => {
  let service: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            estadId: expect.any(Object),
            nomEstado: expect.any(Object),
            idTipousina: expect.any(Object),
            idOnsPequenasusinas: expect.any(Object),
            idOnsUsina: expect.any(Object),
            nomPequenasusinas: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            estadId: expect.any(Object),
            nomEstado: expect.any(Object),
            idTipousina: expect.any(Object),
            idOnsPequenasusinas: expect.any(Object),
            idOnsUsina: expect.any(Object),
            nomPequenasusinas: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
      it('should return NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas for default OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas initial value', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup(sampleWithNewData);

        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = service.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
          formGroup,
        ) as any;

        expect(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas for empty OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas initial value', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup();

        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = service.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
          formGroup,
        ) as any;

        expect(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toMatchObject({});
      });

      it('should return IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup(sampleWithRequiredData);

        const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = service.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
          formGroup,
        ) as any;

        expect(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas should disable id FormControl', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
