import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-detail.component';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal Management Detail Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-detail.component').then(
                  m => m.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent,
                ),
              resolve: { onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: () => of({ id: 18913 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDetailComponent);

      // THEN
      expect(instance.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal()).toEqual(expect.objectContaining({ id: 18913 }));
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
