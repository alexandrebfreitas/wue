import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIntercambioSinComOutrosPaisesService } from '../service/ons-intercambio-sin-com-outros-paises.service';
import { IOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';
import { OnsIntercambioSinComOutrosPaisesFormService } from './ons-intercambio-sin-com-outros-paises-form.service';

import { OnsIntercambioSinComOutrosPaisesUpdateComponent } from './ons-intercambio-sin-com-outros-paises-update.component';

describe('OnsIntercambioSinComOutrosPaises Management Update Component', () => {
  let comp: OnsIntercambioSinComOutrosPaisesUpdateComponent;
  let fixture: ComponentFixture<OnsIntercambioSinComOutrosPaisesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIntercambioSinComOutrosPaisesFormService: OnsIntercambioSinComOutrosPaisesFormService;
  let onsIntercambioSinComOutrosPaisesService: OnsIntercambioSinComOutrosPaisesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIntercambioSinComOutrosPaisesUpdateComponent],
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
      .overrideTemplate(OnsIntercambioSinComOutrosPaisesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIntercambioSinComOutrosPaisesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIntercambioSinComOutrosPaisesFormService = TestBed.inject(OnsIntercambioSinComOutrosPaisesFormService);
    onsIntercambioSinComOutrosPaisesService = TestBed.inject(OnsIntercambioSinComOutrosPaisesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises = { id: 30807 };

      activatedRoute.data = of({ onsIntercambioSinComOutrosPaises });
      comp.ngOnInit();

      expect(comp.onsIntercambioSinComOutrosPaises).toEqual(onsIntercambioSinComOutrosPaises);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIntercambioSinComOutrosPaises>>();
      const onsIntercambioSinComOutrosPaises = { id: 8173 };
      jest
        .spyOn(onsIntercambioSinComOutrosPaisesFormService, 'getOnsIntercambioSinComOutrosPaises')
        .mockReturnValue(onsIntercambioSinComOutrosPaises);
      jest.spyOn(onsIntercambioSinComOutrosPaisesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIntercambioSinComOutrosPaises });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIntercambioSinComOutrosPaises }));
      saveSubject.complete();

      // THEN
      expect(onsIntercambioSinComOutrosPaisesFormService.getOnsIntercambioSinComOutrosPaises).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIntercambioSinComOutrosPaisesService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIntercambioSinComOutrosPaises),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIntercambioSinComOutrosPaises>>();
      const onsIntercambioSinComOutrosPaises = { id: 8173 };
      jest.spyOn(onsIntercambioSinComOutrosPaisesFormService, 'getOnsIntercambioSinComOutrosPaises').mockReturnValue({ id: null });
      jest.spyOn(onsIntercambioSinComOutrosPaisesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIntercambioSinComOutrosPaises: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIntercambioSinComOutrosPaises }));
      saveSubject.complete();

      // THEN
      expect(onsIntercambioSinComOutrosPaisesFormService.getOnsIntercambioSinComOutrosPaises).toHaveBeenCalled();
      expect(onsIntercambioSinComOutrosPaisesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIntercambioSinComOutrosPaises>>();
      const onsIntercambioSinComOutrosPaises = { id: 8173 };
      jest.spyOn(onsIntercambioSinComOutrosPaisesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIntercambioSinComOutrosPaises });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIntercambioSinComOutrosPaisesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
