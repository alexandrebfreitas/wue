import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsFatorCapacidadeGeracaoEolicaESolarService } from '../service/ons-fator-capacidade-geracao-eolica-e-solar.service';
import { IOnsFatorCapacidadeGeracaoEolicaESolar } from '../ons-fator-capacidade-geracao-eolica-e-solar.model';
import { OnsFatorCapacidadeGeracaoEolicaESolarFormService } from './ons-fator-capacidade-geracao-eolica-e-solar-form.service';

import { OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent } from './ons-fator-capacidade-geracao-eolica-e-solar-update.component';

describe('OnsFatorCapacidadeGeracaoEolicaESolar Management Update Component', () => {
  let comp: OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent;
  let fixture: ComponentFixture<OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsFatorCapacidadeGeracaoEolicaESolarFormService: OnsFatorCapacidadeGeracaoEolicaESolarFormService;
  let onsFatorCapacidadeGeracaoEolicaESolarService: OnsFatorCapacidadeGeracaoEolicaESolarService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent],
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
      .overrideTemplate(OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsFatorCapacidadeGeracaoEolicaESolarUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsFatorCapacidadeGeracaoEolicaESolarFormService = TestBed.inject(OnsFatorCapacidadeGeracaoEolicaESolarFormService);
    onsFatorCapacidadeGeracaoEolicaESolarService = TestBed.inject(OnsFatorCapacidadeGeracaoEolicaESolarService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar = { id: 16288 };

      activatedRoute.data = of({ onsFatorCapacidadeGeracaoEolicaESolar });
      comp.ngOnInit();

      expect(comp.onsFatorCapacidadeGeracaoEolicaESolar).toEqual(onsFatorCapacidadeGeracaoEolicaESolar);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>>();
      const onsFatorCapacidadeGeracaoEolicaESolar = { id: 30505 };
      jest
        .spyOn(onsFatorCapacidadeGeracaoEolicaESolarFormService, 'getOnsFatorCapacidadeGeracaoEolicaESolar')
        .mockReturnValue(onsFatorCapacidadeGeracaoEolicaESolar);
      jest.spyOn(onsFatorCapacidadeGeracaoEolicaESolarService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsFatorCapacidadeGeracaoEolicaESolar });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsFatorCapacidadeGeracaoEolicaESolar }));
      saveSubject.complete();

      // THEN
      expect(onsFatorCapacidadeGeracaoEolicaESolarFormService.getOnsFatorCapacidadeGeracaoEolicaESolar).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsFatorCapacidadeGeracaoEolicaESolarService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsFatorCapacidadeGeracaoEolicaESolar),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>>();
      const onsFatorCapacidadeGeracaoEolicaESolar = { id: 30505 };
      jest
        .spyOn(onsFatorCapacidadeGeracaoEolicaESolarFormService, 'getOnsFatorCapacidadeGeracaoEolicaESolar')
        .mockReturnValue({ id: null });
      jest.spyOn(onsFatorCapacidadeGeracaoEolicaESolarService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsFatorCapacidadeGeracaoEolicaESolar: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsFatorCapacidadeGeracaoEolicaESolar }));
      saveSubject.complete();

      // THEN
      expect(onsFatorCapacidadeGeracaoEolicaESolarFormService.getOnsFatorCapacidadeGeracaoEolicaESolar).toHaveBeenCalled();
      expect(onsFatorCapacidadeGeracaoEolicaESolarService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>>();
      const onsFatorCapacidadeGeracaoEolicaESolar = { id: 30505 };
      jest.spyOn(onsFatorCapacidadeGeracaoEolicaESolarService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsFatorCapacidadeGeracaoEolicaESolar });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsFatorCapacidadeGeracaoEolicaESolarService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
