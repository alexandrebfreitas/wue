import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEnaDiarioBaciaService } from '../service/ons-ena-diario-bacia.service';
import { IOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';
import { OnsEnaDiarioBaciaFormService } from './ons-ena-diario-bacia-form.service';

import { OnsEnaDiarioBaciaUpdateComponent } from './ons-ena-diario-bacia-update.component';

describe('OnsEnaDiarioBacia Management Update Component', () => {
  let comp: OnsEnaDiarioBaciaUpdateComponent;
  let fixture: ComponentFixture<OnsEnaDiarioBaciaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEnaDiarioBaciaFormService: OnsEnaDiarioBaciaFormService;
  let onsEnaDiarioBaciaService: OnsEnaDiarioBaciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEnaDiarioBaciaUpdateComponent],
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
      .overrideTemplate(OnsEnaDiarioBaciaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEnaDiarioBaciaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEnaDiarioBaciaFormService = TestBed.inject(OnsEnaDiarioBaciaFormService);
    onsEnaDiarioBaciaService = TestBed.inject(OnsEnaDiarioBaciaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEnaDiarioBacia: IOnsEnaDiarioBacia = { id: 23050 };

      activatedRoute.data = of({ onsEnaDiarioBacia });
      comp.ngOnInit();

      expect(comp.onsEnaDiarioBacia).toEqual(onsEnaDiarioBacia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioBacia>>();
      const onsEnaDiarioBacia = { id: 732 };
      jest.spyOn(onsEnaDiarioBaciaFormService, 'getOnsEnaDiarioBacia').mockReturnValue(onsEnaDiarioBacia);
      jest.spyOn(onsEnaDiarioBaciaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioBacia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioBacia }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioBaciaFormService.getOnsEnaDiarioBacia).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEnaDiarioBaciaService.update).toHaveBeenCalledWith(expect.objectContaining(onsEnaDiarioBacia));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioBacia>>();
      const onsEnaDiarioBacia = { id: 732 };
      jest.spyOn(onsEnaDiarioBaciaFormService, 'getOnsEnaDiarioBacia').mockReturnValue({ id: null });
      jest.spyOn(onsEnaDiarioBaciaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioBacia: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioBacia }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioBaciaFormService.getOnsEnaDiarioBacia).toHaveBeenCalled();
      expect(onsEnaDiarioBaciaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioBacia>>();
      const onsEnaDiarioBacia = { id: 732 };
      jest.spyOn(onsEnaDiarioBaciaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioBacia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEnaDiarioBaciaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
