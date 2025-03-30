import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosGrandezasFluviometricasService } from '../service/ons-dados-grandezas-fluviometricas.service';
import { IOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';
import { OnsDadosGrandezasFluviometricasFormService } from './ons-dados-grandezas-fluviometricas-form.service';

import { OnsDadosGrandezasFluviometricasUpdateComponent } from './ons-dados-grandezas-fluviometricas-update.component';

describe('OnsDadosGrandezasFluviometricas Management Update Component', () => {
  let comp: OnsDadosGrandezasFluviometricasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosGrandezasFluviometricasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosGrandezasFluviometricasFormService: OnsDadosGrandezasFluviometricasFormService;
  let onsDadosGrandezasFluviometricasService: OnsDadosGrandezasFluviometricasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosGrandezasFluviometricasUpdateComponent],
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
      .overrideTemplate(OnsDadosGrandezasFluviometricasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosGrandezasFluviometricasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosGrandezasFluviometricasFormService = TestBed.inject(OnsDadosGrandezasFluviometricasFormService);
    onsDadosGrandezasFluviometricasService = TestBed.inject(OnsDadosGrandezasFluviometricasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas = { id: 18806 };

      activatedRoute.data = of({ onsDadosGrandezasFluviometricas });
      comp.ngOnInit();

      expect(comp.onsDadosGrandezasFluviometricas).toEqual(onsDadosGrandezasFluviometricas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosGrandezasFluviometricas>>();
      const onsDadosGrandezasFluviometricas = { id: 29394 };
      jest
        .spyOn(onsDadosGrandezasFluviometricasFormService, 'getOnsDadosGrandezasFluviometricas')
        .mockReturnValue(onsDadosGrandezasFluviometricas);
      jest.spyOn(onsDadosGrandezasFluviometricasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosGrandezasFluviometricas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosGrandezasFluviometricas }));
      saveSubject.complete();

      // THEN
      expect(onsDadosGrandezasFluviometricasFormService.getOnsDadosGrandezasFluviometricas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosGrandezasFluviometricasService.update).toHaveBeenCalledWith(expect.objectContaining(onsDadosGrandezasFluviometricas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosGrandezasFluviometricas>>();
      const onsDadosGrandezasFluviometricas = { id: 29394 };
      jest.spyOn(onsDadosGrandezasFluviometricasFormService, 'getOnsDadosGrandezasFluviometricas').mockReturnValue({ id: null });
      jest.spyOn(onsDadosGrandezasFluviometricasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosGrandezasFluviometricas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosGrandezasFluviometricas }));
      saveSubject.complete();

      // THEN
      expect(onsDadosGrandezasFluviometricasFormService.getOnsDadosGrandezasFluviometricas).toHaveBeenCalled();
      expect(onsDadosGrandezasFluviometricasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosGrandezasFluviometricas>>();
      const onsDadosGrandezasFluviometricas = { id: 29394 };
      jest.spyOn(onsDadosGrandezasFluviometricasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosGrandezasFluviometricas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosGrandezasFluviometricasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
