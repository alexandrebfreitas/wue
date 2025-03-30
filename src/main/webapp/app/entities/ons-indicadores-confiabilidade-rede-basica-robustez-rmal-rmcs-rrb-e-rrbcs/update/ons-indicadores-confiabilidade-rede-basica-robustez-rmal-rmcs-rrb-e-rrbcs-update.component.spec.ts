import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService } from '../service/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.service';
import { IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs } from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService } from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-form.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent } from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-update.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs Management Update Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService;
  let onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent],
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
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService = TestBed.inject(
      OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService,
    );
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService = TestBed.inject(
      OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
        { id: 285 };

      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs });
      comp.ngOnInit();

      expect(comp.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs).toEqual(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>>();
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = { id: 8009 };
      jest
        .spyOn(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService,
          'getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs',
        )
        .mockReturnValue(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>>();
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = { id: 8009 };
      jest
        .spyOn(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService,
          'getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
      ).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>>();
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = { id: 8009 };
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
