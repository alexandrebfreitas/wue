import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsReservatoriosService } from '../service/ons-reservatorios.service';
import { IOnsReservatorios } from '../ons-reservatorios.model';
import { OnsReservatoriosFormService } from './ons-reservatorios-form.service';

import { OnsReservatoriosUpdateComponent } from './ons-reservatorios-update.component';

describe('OnsReservatorios Management Update Component', () => {
  let comp: OnsReservatoriosUpdateComponent;
  let fixture: ComponentFixture<OnsReservatoriosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsReservatoriosFormService: OnsReservatoriosFormService;
  let onsReservatoriosService: OnsReservatoriosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsReservatoriosUpdateComponent],
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
      .overrideTemplate(OnsReservatoriosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsReservatoriosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsReservatoriosFormService = TestBed.inject(OnsReservatoriosFormService);
    onsReservatoriosService = TestBed.inject(OnsReservatoriosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsReservatorios: IOnsReservatorios = { id: 21014 };

      activatedRoute.data = of({ onsReservatorios });
      comp.ngOnInit();

      expect(comp.onsReservatorios).toEqual(onsReservatorios);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsReservatorios>>();
      const onsReservatorios = { id: 25623 };
      jest.spyOn(onsReservatoriosFormService, 'getOnsReservatorios').mockReturnValue(onsReservatorios);
      jest.spyOn(onsReservatoriosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsReservatorios });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsReservatorios }));
      saveSubject.complete();

      // THEN
      expect(onsReservatoriosFormService.getOnsReservatorios).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsReservatoriosService.update).toHaveBeenCalledWith(expect.objectContaining(onsReservatorios));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsReservatorios>>();
      const onsReservatorios = { id: 25623 };
      jest.spyOn(onsReservatoriosFormService, 'getOnsReservatorios').mockReturnValue({ id: null });
      jest.spyOn(onsReservatoriosService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsReservatorios: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsReservatorios }));
      saveSubject.complete();

      // THEN
      expect(onsReservatoriosFormService.getOnsReservatorios).toHaveBeenCalled();
      expect(onsReservatoriosService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsReservatorios>>();
      const onsReservatorios = { id: 25623 };
      jest.spyOn(onsReservatoriosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsReservatorios });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsReservatoriosService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
