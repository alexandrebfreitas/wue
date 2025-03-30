import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEnaDiarioSubsistemaService } from '../service/ons-ena-diario-subsistema.service';
import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import { OnsEnaDiarioSubsistemaFormService } from './ons-ena-diario-subsistema-form.service';

import { OnsEnaDiarioSubsistemaUpdateComponent } from './ons-ena-diario-subsistema-update.component';

describe('OnsEnaDiarioSubsistema Management Update Component', () => {
  let comp: OnsEnaDiarioSubsistemaUpdateComponent;
  let fixture: ComponentFixture<OnsEnaDiarioSubsistemaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEnaDiarioSubsistemaFormService: OnsEnaDiarioSubsistemaFormService;
  let onsEnaDiarioSubsistemaService: OnsEnaDiarioSubsistemaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEnaDiarioSubsistemaUpdateComponent],
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
      .overrideTemplate(OnsEnaDiarioSubsistemaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEnaDiarioSubsistemaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEnaDiarioSubsistemaFormService = TestBed.inject(OnsEnaDiarioSubsistemaFormService);
    onsEnaDiarioSubsistemaService = TestBed.inject(OnsEnaDiarioSubsistemaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema = { id: 14627 };

      activatedRoute.data = of({ onsEnaDiarioSubsistema });
      comp.ngOnInit();

      expect(comp.onsEnaDiarioSubsistema).toEqual(onsEnaDiarioSubsistema);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioSubsistema>>();
      const onsEnaDiarioSubsistema = { id: 7404 };
      jest.spyOn(onsEnaDiarioSubsistemaFormService, 'getOnsEnaDiarioSubsistema').mockReturnValue(onsEnaDiarioSubsistema);
      jest.spyOn(onsEnaDiarioSubsistemaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioSubsistema });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioSubsistema }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioSubsistemaFormService.getOnsEnaDiarioSubsistema).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEnaDiarioSubsistemaService.update).toHaveBeenCalledWith(expect.objectContaining(onsEnaDiarioSubsistema));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioSubsistema>>();
      const onsEnaDiarioSubsistema = { id: 7404 };
      jest.spyOn(onsEnaDiarioSubsistemaFormService, 'getOnsEnaDiarioSubsistema').mockReturnValue({ id: null });
      jest.spyOn(onsEnaDiarioSubsistemaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioSubsistema: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioSubsistema }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioSubsistemaFormService.getOnsEnaDiarioSubsistema).toHaveBeenCalled();
      expect(onsEnaDiarioSubsistemaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioSubsistema>>();
      const onsEnaDiarioSubsistema = { id: 7404 };
      jest.spyOn(onsEnaDiarioSubsistemaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioSubsistema });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEnaDiarioSubsistemaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
