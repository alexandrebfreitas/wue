import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCapacidadeTransformacaoRedeBasicaService } from '../service/ons-capacidade-transformacao-rede-basica.service';
import { IOnsCapacidadeTransformacaoRedeBasica } from '../ons-capacidade-transformacao-rede-basica.model';
import { OnsCapacidadeTransformacaoRedeBasicaFormService } from './ons-capacidade-transformacao-rede-basica-form.service';

import { OnsCapacidadeTransformacaoRedeBasicaUpdateComponent } from './ons-capacidade-transformacao-rede-basica-update.component';

describe('OnsCapacidadeTransformacaoRedeBasica Management Update Component', () => {
  let comp: OnsCapacidadeTransformacaoRedeBasicaUpdateComponent;
  let fixture: ComponentFixture<OnsCapacidadeTransformacaoRedeBasicaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCapacidadeTransformacaoRedeBasicaFormService: OnsCapacidadeTransformacaoRedeBasicaFormService;
  let onsCapacidadeTransformacaoRedeBasicaService: OnsCapacidadeTransformacaoRedeBasicaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCapacidadeTransformacaoRedeBasicaUpdateComponent],
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
      .overrideTemplate(OnsCapacidadeTransformacaoRedeBasicaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCapacidadeTransformacaoRedeBasicaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCapacidadeTransformacaoRedeBasicaFormService = TestBed.inject(OnsCapacidadeTransformacaoRedeBasicaFormService);
    onsCapacidadeTransformacaoRedeBasicaService = TestBed.inject(OnsCapacidadeTransformacaoRedeBasicaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica = { id: 1198 };

      activatedRoute.data = of({ onsCapacidadeTransformacaoRedeBasica });
      comp.ngOnInit();

      expect(comp.onsCapacidadeTransformacaoRedeBasica).toEqual(onsCapacidadeTransformacaoRedeBasica);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>>();
      const onsCapacidadeTransformacaoRedeBasica = { id: 25632 };
      jest
        .spyOn(onsCapacidadeTransformacaoRedeBasicaFormService, 'getOnsCapacidadeTransformacaoRedeBasica')
        .mockReturnValue(onsCapacidadeTransformacaoRedeBasica);
      jest.spyOn(onsCapacidadeTransformacaoRedeBasicaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCapacidadeTransformacaoRedeBasica });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCapacidadeTransformacaoRedeBasica }));
      saveSubject.complete();

      // THEN
      expect(onsCapacidadeTransformacaoRedeBasicaFormService.getOnsCapacidadeTransformacaoRedeBasica).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCapacidadeTransformacaoRedeBasicaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsCapacidadeTransformacaoRedeBasica),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>>();
      const onsCapacidadeTransformacaoRedeBasica = { id: 25632 };
      jest.spyOn(onsCapacidadeTransformacaoRedeBasicaFormService, 'getOnsCapacidadeTransformacaoRedeBasica').mockReturnValue({ id: null });
      jest.spyOn(onsCapacidadeTransformacaoRedeBasicaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCapacidadeTransformacaoRedeBasica: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCapacidadeTransformacaoRedeBasica }));
      saveSubject.complete();

      // THEN
      expect(onsCapacidadeTransformacaoRedeBasicaFormService.getOnsCapacidadeTransformacaoRedeBasica).toHaveBeenCalled();
      expect(onsCapacidadeTransformacaoRedeBasicaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>>();
      const onsCapacidadeTransformacaoRedeBasica = { id: 25632 };
      jest.spyOn(onsCapacidadeTransformacaoRedeBasicaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCapacidadeTransformacaoRedeBasica });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCapacidadeTransformacaoRedeBasicaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
