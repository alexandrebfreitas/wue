import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEarDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ear-diario-ree-reservatorio-equivalente-energia.service';
import { IOnsEarDiarioReeReservatorioEquivalenteEnergia } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService } from './ons-ear-diario-ree-reservatorio-equivalente-energia-form.service';

import { OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent } from './ons-ear-diario-ree-reservatorio-equivalente-energia-update.component';

describe('OnsEarDiarioReeReservatorioEquivalenteEnergia Management Update Component', () => {
  let comp: OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent;
  let fixture: ComponentFixture<OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEarDiarioReeReservatorioEquivalenteEnergiaFormService: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService;
  let onsEarDiarioReeReservatorioEquivalenteEnergiaService: OnsEarDiarioReeReservatorioEquivalenteEnergiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent],
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
      .overrideTemplate(OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEarDiarioReeReservatorioEquivalenteEnergiaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEarDiarioReeReservatorioEquivalenteEnergiaFormService = TestBed.inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService);
    onsEarDiarioReeReservatorioEquivalenteEnergiaService = TestBed.inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia = { id: 16684 };

      activatedRoute.data = of({ onsEarDiarioReeReservatorioEquivalenteEnergia });
      comp.ngOnInit();

      expect(comp.onsEarDiarioReeReservatorioEquivalenteEnergia).toEqual(onsEarDiarioReeReservatorioEquivalenteEnergia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>>();
      const onsEarDiarioReeReservatorioEquivalenteEnergia = { id: 21274 };
      jest
        .spyOn(onsEarDiarioReeReservatorioEquivalenteEnergiaFormService, 'getOnsEarDiarioReeReservatorioEquivalenteEnergia')
        .mockReturnValue(onsEarDiarioReeReservatorioEquivalenteEnergia);
      jest.spyOn(onsEarDiarioReeReservatorioEquivalenteEnergiaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioReeReservatorioEquivalenteEnergia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioReeReservatorioEquivalenteEnergia }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioReeReservatorioEquivalenteEnergiaFormService.getOnsEarDiarioReeReservatorioEquivalenteEnergia).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEarDiarioReeReservatorioEquivalenteEnergiaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsEarDiarioReeReservatorioEquivalenteEnergia),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>>();
      const onsEarDiarioReeReservatorioEquivalenteEnergia = { id: 21274 };
      jest
        .spyOn(onsEarDiarioReeReservatorioEquivalenteEnergiaFormService, 'getOnsEarDiarioReeReservatorioEquivalenteEnergia')
        .mockReturnValue({ id: null });
      jest.spyOn(onsEarDiarioReeReservatorioEquivalenteEnergiaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioReeReservatorioEquivalenteEnergia: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEarDiarioReeReservatorioEquivalenteEnergia }));
      saveSubject.complete();

      // THEN
      expect(onsEarDiarioReeReservatorioEquivalenteEnergiaFormService.getOnsEarDiarioReeReservatorioEquivalenteEnergia).toHaveBeenCalled();
      expect(onsEarDiarioReeReservatorioEquivalenteEnergiaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>>();
      const onsEarDiarioReeReservatorioEquivalenteEnergia = { id: 21274 };
      jest.spyOn(onsEarDiarioReeReservatorioEquivalenteEnergiaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEarDiarioReeReservatorioEquivalenteEnergia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEarDiarioReeReservatorioEquivalenteEnergiaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
