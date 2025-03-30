import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-geracao-comercial-para-exportacao-internacional.test-samples';

import { OnsGeracaoComercialParaExportacaoInternacionalFormService } from './ons-geracao-comercial-para-exportacao-internacional-form.service';

describe('OnsGeracaoComercialParaExportacaoInternacional Form Service', () => {
  let service: OnsGeracaoComercialParaExportacaoInternacionalFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsGeracaoComercialParaExportacaoInternacionalFormService);
  });

  describe('Service methods', () => {
    describe('createOnsGeracaoComercialParaExportacaoInternacionalFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomConversora: expect.any(Object),
            dinInstante: expect.any(Object),
            valModalidadecontratual: expect.any(Object),
            valModalidadeemergencial: expect.any(Object),
            valModalidadeoportunidade: expect.any(Object),
            valModalidadeteste: expect.any(Object),
            valModalidadeexcepcional: expect.any(Object),
          }),
        );
      });

      it('passing IOnsGeracaoComercialParaExportacaoInternacional should create a new form with FormGroup', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomConversora: expect.any(Object),
            dinInstante: expect.any(Object),
            valModalidadecontratual: expect.any(Object),
            valModalidadeemergencial: expect.any(Object),
            valModalidadeoportunidade: expect.any(Object),
            valModalidadeteste: expect.any(Object),
            valModalidadeexcepcional: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsGeracaoComercialParaExportacaoInternacional', () => {
      it('should return NewOnsGeracaoComercialParaExportacaoInternacional for default OnsGeracaoComercialParaExportacaoInternacional initial value', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup(sampleWithNewData);

        const onsGeracaoComercialParaExportacaoInternacional = service.getOnsGeracaoComercialParaExportacaoInternacional(formGroup) as any;

        expect(onsGeracaoComercialParaExportacaoInternacional).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsGeracaoComercialParaExportacaoInternacional for empty OnsGeracaoComercialParaExportacaoInternacional initial value', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup();

        const onsGeracaoComercialParaExportacaoInternacional = service.getOnsGeracaoComercialParaExportacaoInternacional(formGroup) as any;

        expect(onsGeracaoComercialParaExportacaoInternacional).toMatchObject({});
      });

      it('should return IOnsGeracaoComercialParaExportacaoInternacional', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup(sampleWithRequiredData);

        const onsGeracaoComercialParaExportacaoInternacional = service.getOnsGeracaoComercialParaExportacaoInternacional(formGroup) as any;

        expect(onsGeracaoComercialParaExportacaoInternacional).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsGeracaoComercialParaExportacaoInternacional should not enable id FormControl', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsGeracaoComercialParaExportacaoInternacional should disable id FormControl', () => {
        const formGroup = service.createOnsGeracaoComercialParaExportacaoInternacionalFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
