import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsGeracaoUsinaEmBaseHorariaService } from '../service/ons-geracao-usina-em-base-horaria.service';
import { IOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';
import { OnsGeracaoUsinaEmBaseHorariaFormService } from './ons-geracao-usina-em-base-horaria-form.service';

import { OnsGeracaoUsinaEmBaseHorariaUpdateComponent } from './ons-geracao-usina-em-base-horaria-update.component';

describe('OnsGeracaoUsinaEmBaseHoraria Management Update Component', () => {
  let comp: OnsGeracaoUsinaEmBaseHorariaUpdateComponent;
  let fixture: ComponentFixture<OnsGeracaoUsinaEmBaseHorariaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsGeracaoUsinaEmBaseHorariaFormService: OnsGeracaoUsinaEmBaseHorariaFormService;
  let onsGeracaoUsinaEmBaseHorariaService: OnsGeracaoUsinaEmBaseHorariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsGeracaoUsinaEmBaseHorariaUpdateComponent],
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
      .overrideTemplate(OnsGeracaoUsinaEmBaseHorariaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsGeracaoUsinaEmBaseHorariaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsGeracaoUsinaEmBaseHorariaFormService = TestBed.inject(OnsGeracaoUsinaEmBaseHorariaFormService);
    onsGeracaoUsinaEmBaseHorariaService = TestBed.inject(OnsGeracaoUsinaEmBaseHorariaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria = { id: 5320 };

      activatedRoute.data = of({ onsGeracaoUsinaEmBaseHoraria });
      comp.ngOnInit();

      expect(comp.onsGeracaoUsinaEmBaseHoraria).toEqual(onsGeracaoUsinaEmBaseHoraria);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoUsinaEmBaseHoraria>>();
      const onsGeracaoUsinaEmBaseHoraria = { id: 22430 };
      jest.spyOn(onsGeracaoUsinaEmBaseHorariaFormService, 'getOnsGeracaoUsinaEmBaseHoraria').mockReturnValue(onsGeracaoUsinaEmBaseHoraria);
      jest.spyOn(onsGeracaoUsinaEmBaseHorariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoUsinaEmBaseHoraria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsGeracaoUsinaEmBaseHoraria }));
      saveSubject.complete();

      // THEN
      expect(onsGeracaoUsinaEmBaseHorariaFormService.getOnsGeracaoUsinaEmBaseHoraria).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsGeracaoUsinaEmBaseHorariaService.update).toHaveBeenCalledWith(expect.objectContaining(onsGeracaoUsinaEmBaseHoraria));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoUsinaEmBaseHoraria>>();
      const onsGeracaoUsinaEmBaseHoraria = { id: 22430 };
      jest.spyOn(onsGeracaoUsinaEmBaseHorariaFormService, 'getOnsGeracaoUsinaEmBaseHoraria').mockReturnValue({ id: null });
      jest.spyOn(onsGeracaoUsinaEmBaseHorariaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoUsinaEmBaseHoraria: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsGeracaoUsinaEmBaseHoraria }));
      saveSubject.complete();

      // THEN
      expect(onsGeracaoUsinaEmBaseHorariaFormService.getOnsGeracaoUsinaEmBaseHoraria).toHaveBeenCalled();
      expect(onsGeracaoUsinaEmBaseHorariaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoUsinaEmBaseHoraria>>();
      const onsGeracaoUsinaEmBaseHoraria = { id: 22430 };
      jest.spyOn(onsGeracaoUsinaEmBaseHorariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoUsinaEmBaseHoraria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsGeracaoUsinaEmBaseHorariaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
