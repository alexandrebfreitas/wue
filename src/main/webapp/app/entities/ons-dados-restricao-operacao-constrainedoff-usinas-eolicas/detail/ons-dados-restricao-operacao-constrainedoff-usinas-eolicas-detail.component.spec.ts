import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detail.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas Management Detail Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detail.component').then(
                  m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent,
                ),
              resolve: { onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: () => of({ id: 11763 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetailComponent);

      // THEN
      expect(instance.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas()).toEqual(expect.objectContaining({ id: 11763 }));
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
