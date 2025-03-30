import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEarDiarioBaciaService } from '../service/ons-ear-diario-bacia.service';
import { IOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';
import { OnsEarDiarioBaciaFormService } from './ons-ear-diario-bacia-form.service';

import { OnsEarDiarioBaciaUpdateComponent } from './ons-ear-diario-bacia-update.component';

describe('OnsEarDiarioBacia Management Update Component', () => {
  let comp: OnsEarDiarioBaciaUpdateComponent;
  let fixture: ComponentFixture<OnsEarDiarioBaciaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEarDiarioBaciaFormService: OnsEarDiarioBaciaFormService;
  let onsEarDiarioBaciaService: OnsEarDiarioBaciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEarDiarioBaciaUpdateComponent],
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
      .overrideTemplate(OnsEarDiarioBaciaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEarDiarioBaciaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEarDiarioBaciaFormService = TestBed.inject(OnsEarDiarioBaciaFormService);
    onsEarDiarioBaciaService = TestBed.inject(OnsEarDiarioBaciaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEarDiarioBacia: IOnsEarDiarioBacia = { id: 2174 };

      activatedRoute.data = of({ onsEarDiarioBacia });
      comp.ngOnInit();

      expect(comp.onsEarDiarioBacia).toEqual(onsEarDiarioBacia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioBacia>>();
      const onsEarDiarioBacia = { id: 30574 };
      jest.spyOn(onsEarDiarioBaciaFormService, 'getOnsEarDiarioBacia').mockReturnValue(onsEarDiarioBacia);
      jest.spyOn(onsEarDiarioBaciaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioBacia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioBacia }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioBaciaFormService.getOnsEarDiarioBacia).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEarDiarioBaciaService.update).toHaveBeenCalledWith(expect.objectContaining(onsEarDiarioBacia));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioBacia>>();
      const onsEarDiarioBacia = { id: 30574 };
      jest.spyOn(onsEarDiarioBaciaFormService, 'getOnsEarDiarioBacia').mockReturnValue({ id: null });
      jest.spyOn(onsEarDiarioBaciaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioBacia: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioBacia }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioBaciaFormService.getOnsEarDiarioBacia).toHaveBeenCalled();
      expect(onsEarDiarioBaciaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioBacia>>();
      const onsEarDiarioBacia = { id: 30574 };
      jest.spyOn(onsEarDiarioBaciaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioBacia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEarDiarioBaciaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
