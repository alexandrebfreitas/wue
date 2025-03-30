import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detail.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas Management Detail Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detail.component').then(
                  m => m.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent,
                ),
              resolve: { onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: () => of({ id: 27295 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetailComponent);

      // THEN
      expect(instance.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas()).toEqual(expect.objectContaining({ id: 27295 }));
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
