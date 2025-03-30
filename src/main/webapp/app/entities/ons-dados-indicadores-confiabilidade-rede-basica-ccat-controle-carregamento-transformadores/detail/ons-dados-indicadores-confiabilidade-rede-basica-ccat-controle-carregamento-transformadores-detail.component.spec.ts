import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent } from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-detail.component';

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores Management Detail Component', () => {
  let comp: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import(
                  './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-detail.component'
                ).then(m => m.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent),
              resolve: { onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: () => of({ id: 28122 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl(
        '/',
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresDetailComponent,
      );

      // THEN
      expect(instance.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores()).toEqual(
        expect.objectContaining({ id: 28122 }),
      );
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
