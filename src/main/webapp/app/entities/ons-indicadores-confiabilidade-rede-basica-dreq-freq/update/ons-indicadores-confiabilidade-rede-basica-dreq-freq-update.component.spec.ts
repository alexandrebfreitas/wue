import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';
import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService } from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-form.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent } from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-update.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq Management Update Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService;
  let onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent],
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
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService);
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = { id: 13133 };

      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaDreqFreq });
      comp.ngOnInit();

      expect(comp.onsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toEqual(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>>();
      const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = { id: 4905 };
      jest
        .spyOn(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService, 'getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq')
        .mockReturnValue(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaDreqFreq });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaDreqFreq }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>>();
      const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = { id: 4905 };
      jest
        .spyOn(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService, 'getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq')
        .mockReturnValue({ id: null });
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaDreqFreq }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
      ).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>>();
      const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = { id: 4905 };
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaDreqFreq });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
