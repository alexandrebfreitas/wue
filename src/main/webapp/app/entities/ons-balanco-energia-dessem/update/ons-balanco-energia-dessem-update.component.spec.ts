import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsBalancoEnergiaDessemService } from '../service/ons-balanco-energia-dessem.service';
import { IOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';
import { OnsBalancoEnergiaDessemFormService } from './ons-balanco-energia-dessem-form.service';

import { OnsBalancoEnergiaDessemUpdateComponent } from './ons-balanco-energia-dessem-update.component';

describe('OnsBalancoEnergiaDessem Management Update Component', () => {
  let comp: OnsBalancoEnergiaDessemUpdateComponent;
  let fixture: ComponentFixture<OnsBalancoEnergiaDessemUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsBalancoEnergiaDessemFormService: OnsBalancoEnergiaDessemFormService;
  let onsBalancoEnergiaDessemService: OnsBalancoEnergiaDessemService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsBalancoEnergiaDessemUpdateComponent],
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
      .overrideTemplate(OnsBalancoEnergiaDessemUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsBalancoEnergiaDessemUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsBalancoEnergiaDessemFormService = TestBed.inject(OnsBalancoEnergiaDessemFormService);
    onsBalancoEnergiaDessemService = TestBed.inject(OnsBalancoEnergiaDessemService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem = { id: 10632 };

      activatedRoute.data = of({ onsBalancoEnergiaDessem });
      comp.ngOnInit();

      expect(comp.onsBalancoEnergiaDessem).toEqual(onsBalancoEnergiaDessem);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsBalancoEnergiaDessem>>();
      const onsBalancoEnergiaDessem = { id: 3629 };
      jest.spyOn(onsBalancoEnergiaDessemFormService, 'getOnsBalancoEnergiaDessem').mockReturnValue(onsBalancoEnergiaDessem);
      jest.spyOn(onsBalancoEnergiaDessemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsBalancoEnergiaDessem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsBalancoEnergiaDessem }));
      saveSubject.complete();

      // THEN
      expect(onsBalancoEnergiaDessemFormService.getOnsBalancoEnergiaDessem).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsBalancoEnergiaDessemService.update).toHaveBeenCalledWith(expect.objectContaining(onsBalancoEnergiaDessem));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsBalancoEnergiaDessem>>();
      const onsBalancoEnergiaDessem = { id: 3629 };
      jest.spyOn(onsBalancoEnergiaDessemFormService, 'getOnsBalancoEnergiaDessem').mockReturnValue({ id: null });
      jest.spyOn(onsBalancoEnergiaDessemService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsBalancoEnergiaDessem: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsBalancoEnergiaDessem }));
      saveSubject.complete();

      // THEN
      expect(onsBalancoEnergiaDessemFormService.getOnsBalancoEnergiaDessem).toHaveBeenCalled();
      expect(onsBalancoEnergiaDessemService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsBalancoEnergiaDessem>>();
      const onsBalancoEnergiaDessem = { id: 3629 };
      jest.spyOn(onsBalancoEnergiaDessemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsBalancoEnergiaDessem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsBalancoEnergiaDessemService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
