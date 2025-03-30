import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCvuUsinaTermicasService } from '../service/ons-cvu-usina-termicas.service';
import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';
import { OnsCvuUsinaTermicasFormService } from './ons-cvu-usina-termicas-form.service';

import { OnsCvuUsinaTermicasUpdateComponent } from './ons-cvu-usina-termicas-update.component';

describe('OnsCvuUsinaTermicas Management Update Component', () => {
  let comp: OnsCvuUsinaTermicasUpdateComponent;
  let fixture: ComponentFixture<OnsCvuUsinaTermicasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCvuUsinaTermicasFormService: OnsCvuUsinaTermicasFormService;
  let onsCvuUsinaTermicasService: OnsCvuUsinaTermicasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCvuUsinaTermicasUpdateComponent],
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
      .overrideTemplate(OnsCvuUsinaTermicasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCvuUsinaTermicasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCvuUsinaTermicasFormService = TestBed.inject(OnsCvuUsinaTermicasFormService);
    onsCvuUsinaTermicasService = TestBed.inject(OnsCvuUsinaTermicasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCvuUsinaTermicas: IOnsCvuUsinaTermicas = { id: 16744 };

      activatedRoute.data = of({ onsCvuUsinaTermicas });
      comp.ngOnInit();

      expect(comp.onsCvuUsinaTermicas).toEqual(onsCvuUsinaTermicas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCvuUsinaTermicas>>();
      const onsCvuUsinaTermicas = { id: 30136 };
      jest.spyOn(onsCvuUsinaTermicasFormService, 'getOnsCvuUsinaTermicas').mockReturnValue(onsCvuUsinaTermicas);
      jest.spyOn(onsCvuUsinaTermicasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCvuUsinaTermicas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCvuUsinaTermicas }));
      saveSubject.complete();

      // THEN
      expect(onsCvuUsinaTermicasFormService.getOnsCvuUsinaTermicas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCvuUsinaTermicasService.update).toHaveBeenCalledWith(expect.objectContaining(onsCvuUsinaTermicas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCvuUsinaTermicas>>();
      const onsCvuUsinaTermicas = { id: 30136 };
      jest.spyOn(onsCvuUsinaTermicasFormService, 'getOnsCvuUsinaTermicas').mockReturnValue({ id: null });
      jest.spyOn(onsCvuUsinaTermicasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCvuUsinaTermicas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCvuUsinaTermicas }));
      saveSubject.complete();

      // THEN
      expect(onsCvuUsinaTermicasFormService.getOnsCvuUsinaTermicas).toHaveBeenCalled();
      expect(onsCvuUsinaTermicasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCvuUsinaTermicas>>();
      const onsCvuUsinaTermicas = { id: 30136 };
      jest.spyOn(onsCvuUsinaTermicasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCvuUsinaTermicas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCvuUsinaTermicasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
