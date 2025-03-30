import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ena-diario-ree-reservatorio-equivalente-energia.service';
import { IOnsEnaDiarioReeReservatorioEquivalenteEnergia } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';
import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService } from './ons-ena-diario-ree-reservatorio-equivalente-energia-form.service';

import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent } from './ons-ena-diario-ree-reservatorio-equivalente-energia-update.component';

describe('OnsEnaDiarioReeReservatorioEquivalenteEnergia Management Update Component', () => {
  let comp: OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent;
  let fixture: ComponentFixture<OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService;
  let onsEnaDiarioReeReservatorioEquivalenteEnergiaService: OnsEnaDiarioReeReservatorioEquivalenteEnergiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent],
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
      .overrideTemplate(OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsEnaDiarioReeReservatorioEquivalenteEnergiaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService = TestBed.inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService);
    onsEnaDiarioReeReservatorioEquivalenteEnergiaService = TestBed.inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia = { id: 11180 };

      activatedRoute.data = of({ onsEnaDiarioReeReservatorioEquivalenteEnergia });
      comp.ngOnInit();

      expect(comp.onsEnaDiarioReeReservatorioEquivalenteEnergia).toEqual(onsEnaDiarioReeReservatorioEquivalenteEnergia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>>();
      const onsEnaDiarioReeReservatorioEquivalenteEnergia = { id: 7321 };
      jest
        .spyOn(onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService, 'getOnsEnaDiarioReeReservatorioEquivalenteEnergia')
        .mockReturnValue(onsEnaDiarioReeReservatorioEquivalenteEnergia);
      jest.spyOn(onsEnaDiarioReeReservatorioEquivalenteEnergiaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioReeReservatorioEquivalenteEnergia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioReeReservatorioEquivalenteEnergia }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService.getOnsEnaDiarioReeReservatorioEquivalenteEnergia).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsEnaDiarioReeReservatorioEquivalenteEnergiaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsEnaDiarioReeReservatorioEquivalenteEnergia),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>>();
      const onsEnaDiarioReeReservatorioEquivalenteEnergia = { id: 7321 };
      jest
        .spyOn(onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService, 'getOnsEnaDiarioReeReservatorioEquivalenteEnergia')
        .mockReturnValue({ id: null });
      jest.spyOn(onsEnaDiarioReeReservatorioEquivalenteEnergiaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioReeReservatorioEquivalenteEnergia: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsEnaDiarioReeReservatorioEquivalenteEnergia }));
      saveSubject.complete();

      // THEN
      expect(onsEnaDiarioReeReservatorioEquivalenteEnergiaFormService.getOnsEnaDiarioReeReservatorioEquivalenteEnergia).toHaveBeenCalled();
      expect(onsEnaDiarioReeReservatorioEquivalenteEnergiaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>>();
      const onsEnaDiarioReeReservatorioEquivalenteEnergia = { id: 7321 };
      jest.spyOn(onsEnaDiarioReeReservatorioEquivalenteEnergiaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsEnaDiarioReeReservatorioEquivalenteEnergia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsEnaDiarioReeReservatorioEquivalenteEnergiaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
