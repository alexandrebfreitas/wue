import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEarDiarioReservatorioService } from '../service/ons-ear-diario-reservatorio.service';
import { IOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';
import { OnsEarDiarioReservatorioFormService } from './ons-ear-diario-reservatorio-form.service';

import { OnsEarDiarioReservatorioUpdateComponent } from './ons-ear-diario-reservatorio-update.component';

describe('OnsEarDiarioReservatorio Management Update Component', () => {
  let comp: OnsEarDiarioReservatorioUpdateComponent;
  let fixture: ComponentFixture<OnsEarDiarioReservatorioUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEarDiarioReservatorioFormService: OnsEarDiarioReservatorioFormService;
  let onsEarDiarioReservatorioService: OnsEarDiarioReservatorioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEarDiarioReservatorioUpdateComponent],
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
      .overrideTemplate(OnsEarDiarioReservatorioUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEarDiarioReservatorioUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEarDiarioReservatorioFormService = TestBed.inject(OnsEarDiarioReservatorioFormService);
    onsEarDiarioReservatorioService = TestBed.inject(OnsEarDiarioReservatorioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEarDiarioReservatorio: IOnsEarDiarioReservatorio = { id: 4681 };

      activatedRoute.data = of({ onsEarDiarioReservatorio });
      comp.ngOnInit();

      expect(comp.onsEarDiarioReservatorio).toEqual(onsEarDiarioReservatorio);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioReservatorio>>();
      const onsEarDiarioReservatorio = { id: 29357 };
      jest.spyOn(onsEarDiarioReservatorioFormService, 'getOnsEarDiarioReservatorio').mockReturnValue(onsEarDiarioReservatorio);
      jest.spyOn(onsEarDiarioReservatorioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioReservatorio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioReservatorio }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioReservatorioFormService.getOnsEarDiarioReservatorio).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEarDiarioReservatorioService.update).toHaveBeenCalledWith(expect.objectContaining(onsEarDiarioReservatorio));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioReservatorio>>();
      const onsEarDiarioReservatorio = { id: 29357 };
      jest.spyOn(onsEarDiarioReservatorioFormService, 'getOnsEarDiarioReservatorio').mockReturnValue({ id: null });
      jest.spyOn(onsEarDiarioReservatorioService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioReservatorio: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioReservatorio }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioReservatorioFormService.getOnsEarDiarioReservatorio).toHaveBeenCalled();
      expect(onsEarDiarioReservatorioService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioReservatorio>>();
      const onsEarDiarioReservatorio = { id: 29357 };
      jest.spyOn(onsEarDiarioReservatorioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioReservatorio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEarDiarioReservatorioService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
