import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsBalancoEnergiaNosSubsistemasService } from '../service/ons-balanco-energia-nos-subsistemas.service';
import { IOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';
import { OnsBalancoEnergiaNosSubsistemasFormService } from './ons-balanco-energia-nos-subsistemas-form.service';

import { OnsBalancoEnergiaNosSubsistemasUpdateComponent } from './ons-balanco-energia-nos-subsistemas-update.component';

describe('OnsBalancoEnergiaNosSubsistemas Management Update Component', () => {
  let comp: OnsBalancoEnergiaNosSubsistemasUpdateComponent;
  let fixture: ComponentFixture<OnsBalancoEnergiaNosSubsistemasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsBalancoEnergiaNosSubsistemasFormService: OnsBalancoEnergiaNosSubsistemasFormService;
  let onsBalancoEnergiaNosSubsistemasService: OnsBalancoEnergiaNosSubsistemasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsBalancoEnergiaNosSubsistemasUpdateComponent],
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
      .overrideTemplate(OnsBalancoEnergiaNosSubsistemasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsBalancoEnergiaNosSubsistemasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsBalancoEnergiaNosSubsistemasFormService = TestBed.inject(OnsBalancoEnergiaNosSubsistemasFormService);
    onsBalancoEnergiaNosSubsistemasService = TestBed.inject(OnsBalancoEnergiaNosSubsistemasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas = { id: 19963 };

      activatedRoute.data = of({ onsBalancoEnergiaNosSubsistemas });
      comp.ngOnInit();

      expect(comp.onsBalancoEnergiaNosSubsistemas).toEqual(onsBalancoEnergiaNosSubsistemas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsBalancoEnergiaNosSubsistemas>>();
      const onsBalancoEnergiaNosSubsistemas = { id: 3586 };
      jest
        .spyOn(onsBalancoEnergiaNosSubsistemasFormService, 'getOnsBalancoEnergiaNosSubsistemas')
        .mockReturnValue(onsBalancoEnergiaNosSubsistemas);
      jest.spyOn(onsBalancoEnergiaNosSubsistemasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsBalancoEnergiaNosSubsistemas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsBalancoEnergiaNosSubsistemas }));
      saveSubject.complete();

      // THEN
      expect(onsBalancoEnergiaNosSubsistemasFormService.getOnsBalancoEnergiaNosSubsistemas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsBalancoEnergiaNosSubsistemasService.update).toHaveBeenCalledWith(expect.objectContaining(onsBalancoEnergiaNosSubsistemas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsBalancoEnergiaNosSubsistemas>>();
      const onsBalancoEnergiaNosSubsistemas = { id: 3586 };
      jest.spyOn(onsBalancoEnergiaNosSubsistemasFormService, 'getOnsBalancoEnergiaNosSubsistemas').mockReturnValue({ id: null });
      jest.spyOn(onsBalancoEnergiaNosSubsistemasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsBalancoEnergiaNosSubsistemas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsBalancoEnergiaNosSubsistemas }));
      saveSubject.complete();

      // THEN
      expect(onsBalancoEnergiaNosSubsistemasFormService.getOnsBalancoEnergiaNosSubsistemas).toHaveBeenCalled();
      expect(onsBalancoEnergiaNosSubsistemasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsBalancoEnergiaNosSubsistemas>>();
      const onsBalancoEnergiaNosSubsistemas = { id: 3586 };
      jest.spyOn(onsBalancoEnergiaNosSubsistemasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsBalancoEnergiaNosSubsistemas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsBalancoEnergiaNosSubsistemasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
