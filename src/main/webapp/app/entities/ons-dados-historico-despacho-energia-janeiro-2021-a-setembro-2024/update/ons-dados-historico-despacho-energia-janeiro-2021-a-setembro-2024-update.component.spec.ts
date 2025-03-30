import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';
import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-form.service';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-update.component';

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 Management Update Component', () => {
  let comp: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent;
  let fixture: ComponentFixture<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService;
  let onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent],
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
      .overrideTemplate(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024UpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService = TestBed.inject(
      OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService,
    );
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service = TestBed.inject(
      OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = {
        id: 31479,
      };

      activatedRoute.data = of({ onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 });
      comp.ngOnInit();

      expect(comp.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toEqual(
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>>();
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = { id: 6420 };
      jest
        .spyOn(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService,
          'getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024',
        )
        .mockReturnValue(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024);
      jest.spyOn(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>>();
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = { id: 6420 };
      jest
        .spyOn(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService,
          'getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
      ).toHaveBeenCalled();
      expect(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>>();
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = { id: 6420 };
      jest.spyOn(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
