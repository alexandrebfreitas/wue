import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCurvaCargaHorariaService } from '../service/ons-curva-carga-horaria.service';
import { IOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';
import { OnsCurvaCargaHorariaFormService } from './ons-curva-carga-horaria-form.service';

import { OnsCurvaCargaHorariaUpdateComponent } from './ons-curva-carga-horaria-update.component';

describe('OnsCurvaCargaHoraria Management Update Component', () => {
  let comp: OnsCurvaCargaHorariaUpdateComponent;
  let fixture: ComponentFixture<OnsCurvaCargaHorariaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCurvaCargaHorariaFormService: OnsCurvaCargaHorariaFormService;
  let onsCurvaCargaHorariaService: OnsCurvaCargaHorariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCurvaCargaHorariaUpdateComponent],
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
      .overrideTemplate(OnsCurvaCargaHorariaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCurvaCargaHorariaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCurvaCargaHorariaFormService = TestBed.inject(OnsCurvaCargaHorariaFormService);
    onsCurvaCargaHorariaService = TestBed.inject(OnsCurvaCargaHorariaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCurvaCargaHoraria: IOnsCurvaCargaHoraria = { id: 2557 };

      activatedRoute.data = of({ onsCurvaCargaHoraria });
      comp.ngOnInit();

      expect(comp.onsCurvaCargaHoraria).toEqual(onsCurvaCargaHoraria);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCurvaCargaHoraria>>();
      const onsCurvaCargaHoraria = { id: 21772 };
      jest.spyOn(onsCurvaCargaHorariaFormService, 'getOnsCurvaCargaHoraria').mockReturnValue(onsCurvaCargaHoraria);
      jest.spyOn(onsCurvaCargaHorariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCurvaCargaHoraria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCurvaCargaHoraria }));
      saveSubject.complete();

      // THEN
      expect(onsCurvaCargaHorariaFormService.getOnsCurvaCargaHoraria).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCurvaCargaHorariaService.update).toHaveBeenCalledWith(expect.objectContaining(onsCurvaCargaHoraria));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCurvaCargaHoraria>>();
      const onsCurvaCargaHoraria = { id: 21772 };
      jest.spyOn(onsCurvaCargaHorariaFormService, 'getOnsCurvaCargaHoraria').mockReturnValue({ id: null });
      jest.spyOn(onsCurvaCargaHorariaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCurvaCargaHoraria: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCurvaCargaHoraria }));
      saveSubject.complete();

      // THEN
      expect(onsCurvaCargaHorariaFormService.getOnsCurvaCargaHoraria).toHaveBeenCalled();
      expect(onsCurvaCargaHorariaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCurvaCargaHoraria>>();
      const onsCurvaCargaHoraria = { id: 21772 };
      jest.spyOn(onsCurvaCargaHorariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCurvaCargaHoraria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCurvaCargaHorariaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
