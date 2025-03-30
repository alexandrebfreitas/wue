import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosHidraulicosReservatorioBaseHorariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-horaria.service';
import { IOnsDadosHidraulicosReservatorioBaseHoraria } from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';
import { OnsDadosHidraulicosReservatorioBaseHorariaFormService } from './ons-dados-hidraulicos-reservatorio-base-horaria-form.service';

import { OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent } from './ons-dados-hidraulicos-reservatorio-base-horaria-update.component';

describe('OnsDadosHidraulicosReservatorioBaseHoraria Management Update Component', () => {
  let comp: OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent;
  let fixture: ComponentFixture<OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosHidraulicosReservatorioBaseHorariaFormService: OnsDadosHidraulicosReservatorioBaseHorariaFormService;
  let onsDadosHidraulicosReservatorioBaseHorariaService: OnsDadosHidraulicosReservatorioBaseHorariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent],
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
      .overrideTemplate(OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosHidraulicosReservatorioBaseHorariaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosHidraulicosReservatorioBaseHorariaFormService = TestBed.inject(OnsDadosHidraulicosReservatorioBaseHorariaFormService);
    onsDadosHidraulicosReservatorioBaseHorariaService = TestBed.inject(OnsDadosHidraulicosReservatorioBaseHorariaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria = { id: 3484 };

      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseHoraria });
      comp.ngOnInit();

      expect(comp.onsDadosHidraulicosReservatorioBaseHoraria).toEqual(onsDadosHidraulicosReservatorioBaseHoraria);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>>();
      const onsDadosHidraulicosReservatorioBaseHoraria = { id: 14970 };
      jest
        .spyOn(onsDadosHidraulicosReservatorioBaseHorariaFormService, 'getOnsDadosHidraulicosReservatorioBaseHoraria')
        .mockReturnValue(onsDadosHidraulicosReservatorioBaseHoraria);
      jest.spyOn(onsDadosHidraulicosReservatorioBaseHorariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseHoraria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHidraulicosReservatorioBaseHoraria }));
      saveSubject.complete();

      // THEN
      expect(onsDadosHidraulicosReservatorioBaseHorariaFormService.getOnsDadosHidraulicosReservatorioBaseHoraria).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosHidraulicosReservatorioBaseHorariaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosHidraulicosReservatorioBaseHoraria),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>>();
      const onsDadosHidraulicosReservatorioBaseHoraria = { id: 14970 };
      jest
        .spyOn(onsDadosHidraulicosReservatorioBaseHorariaFormService, 'getOnsDadosHidraulicosReservatorioBaseHoraria')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosHidraulicosReservatorioBaseHorariaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseHoraria: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHidraulicosReservatorioBaseHoraria }));
      saveSubject.complete();

      // THEN
      expect(onsDadosHidraulicosReservatorioBaseHorariaFormService.getOnsDadosHidraulicosReservatorioBaseHoraria).toHaveBeenCalled();
      expect(onsDadosHidraulicosReservatorioBaseHorariaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>>();
      const onsDadosHidraulicosReservatorioBaseHoraria = { id: 14970 };
      jest.spyOn(onsDadosHidraulicosReservatorioBaseHorariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseHoraria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosHidraulicosReservatorioBaseHorariaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
