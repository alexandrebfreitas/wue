import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-detail.component';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual Management Detail Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-detail.component').then(
                  m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent,
                ),
              resolve: { onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: () => of({ id: 28296 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDetailComponent);

      // THEN
      expect(instance.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual()).toEqual(expect.objectContaining({ id: 28296 }));
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
