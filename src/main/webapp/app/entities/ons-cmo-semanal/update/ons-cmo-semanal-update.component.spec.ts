import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCmoSemanalService } from '../service/ons-cmo-semanal.service';
import { IOnsCmoSemanal } from '../ons-cmo-semanal.model';
import { OnsCmoSemanalFormService } from './ons-cmo-semanal-form.service';

import { OnsCmoSemanalUpdateComponent } from './ons-cmo-semanal-update.component';

describe('OnsCmoSemanal Management Update Component', () => {
  let comp: OnsCmoSemanalUpdateComponent;
  let fixture: ComponentFixture<OnsCmoSemanalUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCmoSemanalFormService: OnsCmoSemanalFormService;
  let onsCmoSemanalService: OnsCmoSemanalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCmoSemanalUpdateComponent],
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
      .overrideTemplate(OnsCmoSemanalUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCmoSemanalUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCmoSemanalFormService = TestBed.inject(OnsCmoSemanalFormService);
    onsCmoSemanalService = TestBed.inject(OnsCmoSemanalService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCmoSemanal: IOnsCmoSemanal = { id: 2156 };

      activatedRoute.data = of({ onsCmoSemanal });
      comp.ngOnInit();

      expect(comp.onsCmoSemanal).toEqual(onsCmoSemanal);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCmoSemanal>>();
      const onsCmoSemanal = { id: 18466 };
      jest.spyOn(onsCmoSemanalFormService, 'getOnsCmoSemanal').mockReturnValue(onsCmoSemanal);
      jest.spyOn(onsCmoSemanalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCmoSemanal });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCmoSemanal }));
      saveSubject.complete();

      // THEN
      expect(onsCmoSemanalFormService.getOnsCmoSemanal).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCmoSemanalService.update).toHaveBeenCalledWith(expect.objectContaining(onsCmoSemanal));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCmoSemanal>>();
      const onsCmoSemanal = { id: 18466 };
      jest.spyOn(onsCmoSemanalFormService, 'getOnsCmoSemanal').mockReturnValue({ id: null });
      jest.spyOn(onsCmoSemanalService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCmoSemanal: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCmoSemanal }));
      saveSubject.complete();

      // THEN
      expect(onsCmoSemanalFormService.getOnsCmoSemanal).toHaveBeenCalled();
      expect(onsCmoSemanalService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCmoSemanal>>();
      const onsCmoSemanal = { id: 18466 };
      jest.spyOn(onsCmoSemanalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCmoSemanal });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCmoSemanalService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
