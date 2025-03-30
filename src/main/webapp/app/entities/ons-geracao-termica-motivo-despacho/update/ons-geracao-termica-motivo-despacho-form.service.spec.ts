import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-geracao-termica-motivo-despacho.test-samples';

import { OnsGeracaoTermicaMotivoDespachoFormService } from './ons-geracao-termica-motivo-despacho-form.service';

describe('OnsGeracaoTermicaMotivoDespacho Form Service', () => {
  let service: OnsGeracaoTermicaMotivoDespachoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsGeracaoTermicaMotivoDespachoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsGeracaoTermicaMotivoDespachoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            nomTipopatamar: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            nomUsina: expect.any(Object),
            codUsinaplanejamento: expect.any(Object),
            ceg: expect.any(Object),
            valProggeracao: expect.any(Object),
            valProgordemmerito: expect.any(Object),
            valProgordemdemeritoref: expect.any(Object),
            valProgordemdemeritoacimadainflex: expect.any(Object),
            valProginflexibilidade: expect.any(Object),
            valProginflexembutmerito: expect.any(Object),
            valProginflexpura: expect.any(Object),
            valPrograzaoeletrica: expect.any(Object),
            valProggarantiaenergetica: expect.any(Object),
            valProggfom: expect.any(Object),
            valProgreposicaoperdas: expect.any(Object),
            valProgexportacao: expect.any(Object),
            valProgreservapotencia: expect.any(Object),
            valProggsub: expect.any(Object),
            valProgunitcommitment: expect.any(Object),
            valProgconstrainedoff: expect.any(Object),
            valProginflexibilidadedessem: expect.any(Object),
            valVerifgeracao: expect.any(Object),
            valVerifordemmerito: expect.any(Object),
            valVerifordemdemeritoacimadainflex: expect.any(Object),
            valVerifinflexibilidade: expect.any(Object),
            valVerifinflexembutmerito: expect.any(Object),
            valVerifinflexpura: expect.any(Object),
            valVerifrazaoeletrica: expect.any(Object),
            valVerifgarantiaenergetica: expect.any(Object),
            valVerifgfom: expect.any(Object),
            valVerifreposicaoperdas: expect.any(Object),
            valVerifexportacao: expect.any(Object),
            valFdexp: expect.any(Object),
            valVerifreservapotencia: expect.any(Object),
            valAtendsatisfatoriorpo: expect.any(Object),
            valVerifgsub: expect.any(Object),
            valVerifunitcommitment: expect.any(Object),
            valVerifconstrainedoff: expect.any(Object),
            tipRestricaoeletrica: expect.any(Object),
          }),
        );
      });

      it('passing IOnsGeracaoTermicaMotivoDespacho should create a new form with FormGroup', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            nomTipopatamar: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            nomUsina: expect.any(Object),
            codUsinaplanejamento: expect.any(Object),
            ceg: expect.any(Object),
            valProggeracao: expect.any(Object),
            valProgordemmerito: expect.any(Object),
            valProgordemdemeritoref: expect.any(Object),
            valProgordemdemeritoacimadainflex: expect.any(Object),
            valProginflexibilidade: expect.any(Object),
            valProginflexembutmerito: expect.any(Object),
            valProginflexpura: expect.any(Object),
            valPrograzaoeletrica: expect.any(Object),
            valProggarantiaenergetica: expect.any(Object),
            valProggfom: expect.any(Object),
            valProgreposicaoperdas: expect.any(Object),
            valProgexportacao: expect.any(Object),
            valProgreservapotencia: expect.any(Object),
            valProggsub: expect.any(Object),
            valProgunitcommitment: expect.any(Object),
            valProgconstrainedoff: expect.any(Object),
            valProginflexibilidadedessem: expect.any(Object),
            valVerifgeracao: expect.any(Object),
            valVerifordemmerito: expect.any(Object),
            valVerifordemdemeritoacimadainflex: expect.any(Object),
            valVerifinflexibilidade: expect.any(Object),
            valVerifinflexembutmerito: expect.any(Object),
            valVerifinflexpura: expect.any(Object),
            valVerifrazaoeletrica: expect.any(Object),
            valVerifgarantiaenergetica: expect.any(Object),
            valVerifgfom: expect.any(Object),
            valVerifreposicaoperdas: expect.any(Object),
            valVerifexportacao: expect.any(Object),
            valFdexp: expect.any(Object),
            valVerifreservapotencia: expect.any(Object),
            valAtendsatisfatoriorpo: expect.any(Object),
            valVerifgsub: expect.any(Object),
            valVerifunitcommitment: expect.any(Object),
            valVerifconstrainedoff: expect.any(Object),
            tipRestricaoeletrica: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsGeracaoTermicaMotivoDespacho', () => {
      it('should return NewOnsGeracaoTermicaMotivoDespacho for default OnsGeracaoTermicaMotivoDespacho initial value', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup(sampleWithNewData);

        const onsGeracaoTermicaMotivoDespacho = service.getOnsGeracaoTermicaMotivoDespacho(formGroup) as any;

        expect(onsGeracaoTermicaMotivoDespacho).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsGeracaoTermicaMotivoDespacho for empty OnsGeracaoTermicaMotivoDespacho initial value', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup();

        const onsGeracaoTermicaMotivoDespacho = service.getOnsGeracaoTermicaMotivoDespacho(formGroup) as any;

        expect(onsGeracaoTermicaMotivoDespacho).toMatchObject({});
      });

      it('should return IOnsGeracaoTermicaMotivoDespacho', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup(sampleWithRequiredData);

        const onsGeracaoTermicaMotivoDespacho = service.getOnsGeracaoTermicaMotivoDespacho(formGroup) as any;

        expect(onsGeracaoTermicaMotivoDespacho).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsGeracaoTermicaMotivoDespacho should not enable id FormControl', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsGeracaoTermicaMotivoDespacho should disable id FormControl', () => {
        const formGroup = service.createOnsGeracaoTermicaMotivoDespachoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
