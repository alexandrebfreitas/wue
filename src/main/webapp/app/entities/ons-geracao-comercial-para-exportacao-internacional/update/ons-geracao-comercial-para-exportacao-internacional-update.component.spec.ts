import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsGeracaoComercialParaExportacaoInternacionalService } from '../service/ons-geracao-comercial-para-exportacao-internacional.service';
import { IOnsGeracaoComercialParaExportacaoInternacional } from '../ons-geracao-comercial-para-exportacao-internacional.model';
import { OnsGeracaoComercialParaExportacaoInternacionalFormService } from './ons-geracao-comercial-para-exportacao-internacional-form.service';

import { OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent } from './ons-geracao-comercial-para-exportacao-internacional-update.component';

describe('OnsGeracaoComercialParaExportacaoInternacional Management Update Component', () => {
  let comp: OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent;
  let fixture: ComponentFixture<OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsGeracaoComercialParaExportacaoInternacionalFormService: OnsGeracaoComercialParaExportacaoInternacionalFormService;
  let onsGeracaoComercialParaExportacaoInternacionalService: OnsGeracaoComercialParaExportacaoInternacionalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsGeracaoComercialParaExportacaoInternacionalUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsGeracaoComercialParaExportacaoInternacionalFormService = TestBed.inject(OnsGeracaoComercialParaExportacaoInternacionalFormService);
    onsGeracaoComercialParaExportacaoInternacionalService = TestBed.inject(OnsGeracaoComercialParaExportacaoInternacionalService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional = { id: 32650 };

      activatedRoute.data = of({ onsGeracaoComercialParaExportacaoInternacional });
      comp.ngOnInit();

      expect(comp.onsGeracaoComercialParaExportacaoInternacional).toEqual(onsGeracaoComercialParaExportacaoInternacional);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>>();
      const onsGeracaoComercialParaExportacaoInternacional = { id: 18041 };
      jest
        .spyOn(onsGeracaoComercialParaExportacaoInternacionalFormService, 'getOnsGeracaoComercialParaExportacaoInternacional')
        .mockReturnValue(onsGeracaoComercialParaExportacaoInternacional);
      jest.spyOn(onsGeracaoComercialParaExportacaoInternacionalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoComercialParaExportacaoInternacional });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsGeracaoComercialParaExportacaoInternacional }));
      saveSubject.complete();

      // THEN
      expect(
        onsGeracaoComercialParaExportacaoInternacionalFormService.getOnsGeracaoComercialParaExportacaoInternacional,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsGeracaoComercialParaExportacaoInternacionalService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsGeracaoComercialParaExportacaoInternacional),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>>();
      const onsGeracaoComercialParaExportacaoInternacional = { id: 18041 };
      jest
        .spyOn(onsGeracaoComercialParaExportacaoInternacionalFormService, 'getOnsGeracaoComercialParaExportacaoInternacional')
        .mockReturnValue({ id: null });
      jest.spyOn(onsGeracaoComercialParaExportacaoInternacionalService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoComercialParaExportacaoInternacional: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsGeracaoComercialParaExportacaoInternacional }));
      saveSubject.complete();

      // THEN
      expect(
        onsGeracaoComercialParaExportacaoInternacionalFormService.getOnsGeracaoComercialParaExportacaoInternacional,
      ).toHaveBeenCalled();
      expect(onsGeracaoComercialParaExportacaoInternacionalService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>>();
      const onsGeracaoComercialParaExportacaoInternacional = { id: 18041 };
      jest.spyOn(onsGeracaoComercialParaExportacaoInternacionalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoComercialParaExportacaoInternacional });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsGeracaoComercialParaExportacaoInternacionalService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
