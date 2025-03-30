import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEarDiarioSubsistemaService } from '../service/ons-ear-diario-subsistema.service';
import { IOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';
import { OnsEarDiarioSubsistemaFormService } from './ons-ear-diario-subsistema-form.service';

import { OnsEarDiarioSubsistemaUpdateComponent } from './ons-ear-diario-subsistema-update.component';

describe('OnsEarDiarioSubsistema Management Update Component', () => {
  let comp: OnsEarDiarioSubsistemaUpdateComponent;
  let fixture: ComponentFixture<OnsEarDiarioSubsistemaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEarDiarioSubsistemaFormService: OnsEarDiarioSubsistemaFormService;
  let onsEarDiarioSubsistemaService: OnsEarDiarioSubsistemaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEarDiarioSubsistemaUpdateComponent],
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
      .overrideTemplate(OnsEarDiarioSubsistemaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEarDiarioSubsistemaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEarDiarioSubsistemaFormService = TestBed.inject(OnsEarDiarioSubsistemaFormService);
    onsEarDiarioSubsistemaService = TestBed.inject(OnsEarDiarioSubsistemaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEarDiarioSubsistema: IOnsEarDiarioSubsistema = { id: 18839 };

      activatedRoute.data = of({ onsEarDiarioSubsistema });
      comp.ngOnInit();

      expect(comp.onsEarDiarioSubsistema).toEqual(onsEarDiarioSubsistema);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioSubsistema>>();
      const onsEarDiarioSubsistema = { id: 31637 };
      jest.spyOn(onsEarDiarioSubsistemaFormService, 'getOnsEarDiarioSubsistema').mockReturnValue(onsEarDiarioSubsistema);
      jest.spyOn(onsEarDiarioSubsistemaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioSubsistema });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioSubsistema }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioSubsistemaFormService.getOnsEarDiarioSubsistema).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEarDiarioSubsistemaService.update).toHaveBeenCalledWith(expect.objectContaining(onsEarDiarioSubsistema));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioSubsistema>>();
      const onsEarDiarioSubsistema = { id: 31637 };
      jest.spyOn(onsEarDiarioSubsistemaFormService, 'getOnsEarDiarioSubsistema').mockReturnValue({ id: null });
      jest.spyOn(onsEarDiarioSubsistemaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioSubsistema: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioSubsistema }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioSubsistemaFormService.getOnsEarDiarioSubsistema).toHaveBeenCalled();
      expect(onsEarDiarioSubsistemaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioSubsistema>>();
      const onsEarDiarioSubsistema = { id: 31637 };
      jest.spyOn(onsEarDiarioSubsistemaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioSubsistema });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEarDiarioSubsistemaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
