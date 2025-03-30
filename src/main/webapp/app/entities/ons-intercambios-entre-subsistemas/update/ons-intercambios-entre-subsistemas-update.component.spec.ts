import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIntercambiosEntreSubsistemasService } from '../service/ons-intercambios-entre-subsistemas.service';
import { IOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';
import { OnsIntercambiosEntreSubsistemasFormService } from './ons-intercambios-entre-subsistemas-form.service';

import { OnsIntercambiosEntreSubsistemasUpdateComponent } from './ons-intercambios-entre-subsistemas-update.component';

describe('OnsIntercambiosEntreSubsistemas Management Update Component', () => {
  let comp: OnsIntercambiosEntreSubsistemasUpdateComponent;
  let fixture: ComponentFixture<OnsIntercambiosEntreSubsistemasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIntercambiosEntreSubsistemasFormService: OnsIntercambiosEntreSubsistemasFormService;
  let onsIntercambiosEntreSubsistemasService: OnsIntercambiosEntreSubsistemasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIntercambiosEntreSubsistemasUpdateComponent],
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
      .overrideTemplate(OnsIntercambiosEntreSubsistemasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIntercambiosEntreSubsistemasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIntercambiosEntreSubsistemasFormService = TestBed.inject(OnsIntercambiosEntreSubsistemasFormService);
    onsIntercambiosEntreSubsistemasService = TestBed.inject(OnsIntercambiosEntreSubsistemasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas = { id: 32412 };

      activatedRoute.data = of({ onsIntercambiosEntreSubsistemas });
      comp.ngOnInit();

      expect(comp.onsIntercambiosEntreSubsistemas).toEqual(onsIntercambiosEntreSubsistemas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIntercambiosEntreSubsistemas>>();
      const onsIntercambiosEntreSubsistemas = { id: 25404 };
      jest
        .spyOn(onsIntercambiosEntreSubsistemasFormService, 'getOnsIntercambiosEntreSubsistemas')
        .mockReturnValue(onsIntercambiosEntreSubsistemas);
      jest.spyOn(onsIntercambiosEntreSubsistemasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIntercambiosEntreSubsistemas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIntercambiosEntreSubsistemas }));
      saveSubject.complete();

      // THEN
      expect(onsIntercambiosEntreSubsistemasFormService.getOnsIntercambiosEntreSubsistemas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIntercambiosEntreSubsistemasService.update).toHaveBeenCalledWith(expect.objectContaining(onsIntercambiosEntreSubsistemas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIntercambiosEntreSubsistemas>>();
      const onsIntercambiosEntreSubsistemas = { id: 25404 };
      jest.spyOn(onsIntercambiosEntreSubsistemasFormService, 'getOnsIntercambiosEntreSubsistemas').mockReturnValue({ id: null });
      jest.spyOn(onsIntercambiosEntreSubsistemasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIntercambiosEntreSubsistemas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIntercambiosEntreSubsistemas }));
      saveSubject.complete();

      // THEN
      expect(onsIntercambiosEntreSubsistemasFormService.getOnsIntercambiosEntreSubsistemas).toHaveBeenCalled();
      expect(onsIntercambiosEntreSubsistemasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIntercambiosEntreSubsistemas>>();
      const onsIntercambiosEntreSubsistemas = { id: 25404 };
      jest.spyOn(onsIntercambiosEntreSubsistemasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIntercambiosEntreSubsistemas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIntercambiosEntreSubsistemasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
