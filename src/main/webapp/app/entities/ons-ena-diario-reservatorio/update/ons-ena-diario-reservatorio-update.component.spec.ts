import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEnaDiarioReservatorioService } from '../service/ons-ena-diario-reservatorio.service';
import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import { OnsEnaDiarioReservatorioFormService } from './ons-ena-diario-reservatorio-form.service';

import { OnsEnaDiarioReservatorioUpdateComponent } from './ons-ena-diario-reservatorio-update.component';

describe('OnsEnaDiarioReservatorio Management Update Component', () => {
  let comp: OnsEnaDiarioReservatorioUpdateComponent;
  let fixture: ComponentFixture<OnsEnaDiarioReservatorioUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEnaDiarioReservatorioFormService: OnsEnaDiarioReservatorioFormService;
  let onsEnaDiarioReservatorioService: OnsEnaDiarioReservatorioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEnaDiarioReservatorioUpdateComponent],
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
      .overrideTemplate(OnsEnaDiarioReservatorioUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEnaDiarioReservatorioUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEnaDiarioReservatorioFormService = TestBed.inject(OnsEnaDiarioReservatorioFormService);
    onsEnaDiarioReservatorioService = TestBed.inject(OnsEnaDiarioReservatorioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio = { id: 3628 };

      activatedRoute.data = of({ onsEnaDiarioReservatorio });
      comp.ngOnInit();

      expect(comp.onsEnaDiarioReservatorio).toEqual(onsEnaDiarioReservatorio);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioReservatorio>>();
      const onsEnaDiarioReservatorio = { id: 28943 };
      jest.spyOn(onsEnaDiarioReservatorioFormService, 'getOnsEnaDiarioReservatorio').mockReturnValue(onsEnaDiarioReservatorio);
      jest.spyOn(onsEnaDiarioReservatorioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioReservatorio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioReservatorio }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioReservatorioFormService.getOnsEnaDiarioReservatorio).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEnaDiarioReservatorioService.update).toHaveBeenCalledWith(expect.objectContaining(onsEnaDiarioReservatorio));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioReservatorio>>();
      const onsEnaDiarioReservatorio = { id: 28943 };
      jest.spyOn(onsEnaDiarioReservatorioFormService, 'getOnsEnaDiarioReservatorio').mockReturnValue({ id: null });
      jest.spyOn(onsEnaDiarioReservatorioService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioReservatorio: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioReservatorio }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioReservatorioFormService.getOnsEnaDiarioReservatorio).toHaveBeenCalled();
      expect(onsEnaDiarioReservatorioService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioReservatorio>>();
      const onsEnaDiarioReservatorio = { id: 28943 };
      jest.spyOn(onsEnaDiarioReservatorioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioReservatorio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEnaDiarioReservatorioService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
