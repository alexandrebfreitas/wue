import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCmoSemihorarioService } from '../service/ons-cmo-semihorario.service';
import { IOnsCmoSemihorario } from '../ons-cmo-semihorario.model';
import { OnsCmoSemihorarioFormService } from './ons-cmo-semihorario-form.service';

import { OnsCmoSemihorarioUpdateComponent } from './ons-cmo-semihorario-update.component';

describe('OnsCmoSemihorario Management Update Component', () => {
  let comp: OnsCmoSemihorarioUpdateComponent;
  let fixture: ComponentFixture<OnsCmoSemihorarioUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCmoSemihorarioFormService: OnsCmoSemihorarioFormService;
  let onsCmoSemihorarioService: OnsCmoSemihorarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCmoSemihorarioUpdateComponent],
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
      .overrideTemplate(OnsCmoSemihorarioUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCmoSemihorarioUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCmoSemihorarioFormService = TestBed.inject(OnsCmoSemihorarioFormService);
    onsCmoSemihorarioService = TestBed.inject(OnsCmoSemihorarioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCmoSemihorario: IOnsCmoSemihorario = { id: 15015 };

      activatedRoute.data = of({ onsCmoSemihorario });
      comp.ngOnInit();

      expect(comp.onsCmoSemihorario).toEqual(onsCmoSemihorario);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCmoSemihorario>>();
      const onsCmoSemihorario = { id: 15398 };
      jest.spyOn(onsCmoSemihorarioFormService, 'getOnsCmoSemihorario').mockReturnValue(onsCmoSemihorario);
      jest.spyOn(onsCmoSemihorarioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCmoSemihorario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCmoSemihorario }));
      saveSubject.complete();

      // THEN
      expect(onsCmoSemihorarioFormService.getOnsCmoSemihorario).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCmoSemihorarioService.update).toHaveBeenCalledWith(expect.objectContaining(onsCmoSemihorario));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCmoSemihorario>>();
      const onsCmoSemihorario = { id: 15398 };
      jest.spyOn(onsCmoSemihorarioFormService, 'getOnsCmoSemihorario').mockReturnValue({ id: null });
      jest.spyOn(onsCmoSemihorarioService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCmoSemihorario: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCmoSemihorario }));
      saveSubject.complete();

      // THEN
      expect(onsCmoSemihorarioFormService.getOnsCmoSemihorario).toHaveBeenCalled();
      expect(onsCmoSemihorarioService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCmoSemihorario>>();
      const onsCmoSemihorario = { id: 15398 };
      jest.spyOn(onsCmoSemihorarioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCmoSemihorario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCmoSemihorarioService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
