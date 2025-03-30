import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosDisponibilidadeUsinasService } from '../service/ons-dados-disponibilidade-usinas.service';
import { IOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';
import { OnsDadosDisponibilidadeUsinasFormService } from './ons-dados-disponibilidade-usinas-form.service';

import { OnsDadosDisponibilidadeUsinasUpdateComponent } from './ons-dados-disponibilidade-usinas-update.component';

describe('OnsDadosDisponibilidadeUsinas Management Update Component', () => {
  let comp: OnsDadosDisponibilidadeUsinasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosDisponibilidadeUsinasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosDisponibilidadeUsinasFormService: OnsDadosDisponibilidadeUsinasFormService;
  let onsDadosDisponibilidadeUsinasService: OnsDadosDisponibilidadeUsinasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosDisponibilidadeUsinasUpdateComponent],
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
      .overrideTemplate(OnsDadosDisponibilidadeUsinasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosDisponibilidadeUsinasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosDisponibilidadeUsinasFormService = TestBed.inject(OnsDadosDisponibilidadeUsinasFormService);
    onsDadosDisponibilidadeUsinasService = TestBed.inject(OnsDadosDisponibilidadeUsinasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas = { id: 10417 };

      activatedRoute.data = of({ onsDadosDisponibilidadeUsinas });
      comp.ngOnInit();

      expect(comp.onsDadosDisponibilidadeUsinas).toEqual(onsDadosDisponibilidadeUsinas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosDisponibilidadeUsinas>>();
      const onsDadosDisponibilidadeUsinas = { id: 14685 };
      jest
        .spyOn(onsDadosDisponibilidadeUsinasFormService, 'getOnsDadosDisponibilidadeUsinas')
        .mockReturnValue(onsDadosDisponibilidadeUsinas);
      jest.spyOn(onsDadosDisponibilidadeUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosDisponibilidadeUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosDisponibilidadeUsinas }));
      saveSubject.complete();

      // THEN
      expect(onsDadosDisponibilidadeUsinasFormService.getOnsDadosDisponibilidadeUsinas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosDisponibilidadeUsinasService.update).toHaveBeenCalledWith(expect.objectContaining(onsDadosDisponibilidadeUsinas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosDisponibilidadeUsinas>>();
      const onsDadosDisponibilidadeUsinas = { id: 14685 };
      jest.spyOn(onsDadosDisponibilidadeUsinasFormService, 'getOnsDadosDisponibilidadeUsinas').mockReturnValue({ id: null });
      jest.spyOn(onsDadosDisponibilidadeUsinasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosDisponibilidadeUsinas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosDisponibilidadeUsinas }));
      saveSubject.complete();

      // THEN
      expect(onsDadosDisponibilidadeUsinasFormService.getOnsDadosDisponibilidadeUsinas).toHaveBeenCalled();
      expect(onsDadosDisponibilidadeUsinasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosDisponibilidadeUsinas>>();
      const onsDadosDisponibilidadeUsinas = { id: 14685 };
      jest.spyOn(onsDadosDisponibilidadeUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosDisponibilidadeUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosDisponibilidadeUsinasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
