import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEquipamentosControleReativosService } from '../service/ons-equipamentos-controle-reativos.service';
import { IOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';
import { OnsEquipamentosControleReativosFormService } from './ons-equipamentos-controle-reativos-form.service';

import { OnsEquipamentosControleReativosUpdateComponent } from './ons-equipamentos-controle-reativos-update.component';

describe('OnsEquipamentosControleReativos Management Update Component', () => {
  let comp: OnsEquipamentosControleReativosUpdateComponent;
  let fixture: ComponentFixture<OnsEquipamentosControleReativosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEquipamentosControleReativosFormService: OnsEquipamentosControleReativosFormService;
  let onsEquipamentosControleReativosService: OnsEquipamentosControleReativosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEquipamentosControleReativosUpdateComponent],
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
      .overrideTemplate(OnsEquipamentosControleReativosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEquipamentosControleReativosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEquipamentosControleReativosFormService = TestBed.inject(OnsEquipamentosControleReativosFormService);
    onsEquipamentosControleReativosService = TestBed.inject(OnsEquipamentosControleReativosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEquipamentosControleReativos: IOnsEquipamentosControleReativos = { id: 17574 };

      activatedRoute.data = of({ onsEquipamentosControleReativos });
      comp.ngOnInit();

      expect(comp.onsEquipamentosControleReativos).toEqual(onsEquipamentosControleReativos);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEquipamentosControleReativos>>();
      const onsEquipamentosControleReativos = { id: 122 };
      jest
        .spyOn(onsEquipamentosControleReativosFormService, 'getOnsEquipamentosControleReativos')
        .mockReturnValue(onsEquipamentosControleReativos);
      jest.spyOn(onsEquipamentosControleReativosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEquipamentosControleReativos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEquipamentosControleReativos }));
      saveSubject.complete();

      // THEN
      expect(onsEquipamentosControleReativosFormService.getOnsEquipamentosControleReativos).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEquipamentosControleReativosService.update).toHaveBeenCalledWith(expect.objectContaining(onsEquipamentosControleReativos));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEquipamentosControleReativos>>();
      const onsEquipamentosControleReativos = { id: 122 };
      jest.spyOn(onsEquipamentosControleReativosFormService, 'getOnsEquipamentosControleReativos').mockReturnValue({ id: null });
      jest.spyOn(onsEquipamentosControleReativosService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEquipamentosControleReativos: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEquipamentosControleReativos }));
      saveSubject.complete();

      // THEN
      expect(onsEquipamentosControleReativosFormService.getOnsEquipamentosControleReativos).toHaveBeenCalled();
      expect(onsEquipamentosControleReativosService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEquipamentosControleReativos>>();
      const onsEquipamentosControleReativos = { id: 122 };
      jest.spyOn(onsEquipamentosControleReativosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEquipamentosControleReativos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEquipamentosControleReativosService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
