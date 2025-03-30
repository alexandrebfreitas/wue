import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.service';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos } from '../ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService } from './ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-form.service';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent } from './ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos-update.component';

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos Management Update Component', () => {
  let comp: OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService: OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService;
  let onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService: OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent],
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
      .overrideTemplate(OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService = TestBed.inject(
      OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService,
    );
    onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService = TestBed.inject(
      OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos: IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos =
        { id: 27369 };

      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos });
      comp.ngOnInit();

      expect(comp.onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos).toEqual(
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos>>();
      const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = { id: 11269 };
      jest
        .spyOn(
          onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService,
          'getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos',
        )
        .mockReturnValue(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos);
      jest
        .spyOn(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService, 'update')
        .mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService.getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos>>();
      const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = { id: 11269 };
      jest
        .spyOn(
          onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService,
          'getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos',
        )
        .mockReturnValue({ id: null });
      jest
        .spyOn(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService, 'create')
        .mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosFormService.getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos,
      ).toHaveBeenCalled();
      expect(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos>>();
      const onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = { id: 11269 };
      jest
        .spyOn(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService, 'update')
        .mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
