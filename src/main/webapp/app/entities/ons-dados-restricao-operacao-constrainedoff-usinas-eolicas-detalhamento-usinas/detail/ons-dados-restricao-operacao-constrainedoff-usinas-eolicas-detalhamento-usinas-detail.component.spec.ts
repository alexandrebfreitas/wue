import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-detail.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas Management Detail Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-detail.component').then(
                  m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent,
                ),
              resolve: { onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: () => of({ id: 7419 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl(
        '/',
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasDetailComponent,
      );

      // THEN
      expect(instance.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas()).toEqual(
        expect.objectContaining({ id: 7419 }),
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
