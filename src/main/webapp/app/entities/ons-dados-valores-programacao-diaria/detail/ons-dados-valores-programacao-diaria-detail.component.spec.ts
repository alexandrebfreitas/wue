import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosValoresProgramacaoDiariaDetailComponent } from './ons-dados-valores-programacao-diaria-detail.component';

describe('OnsDadosValoresProgramacaoDiaria Management Detail Component', () => {
  let comp: OnsDadosValoresProgramacaoDiariaDetailComponent;
  let fixture: ComponentFixture<OnsDadosValoresProgramacaoDiariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosValoresProgramacaoDiariaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-valores-programacao-diaria-detail.component').then(
                  m => m.OnsDadosValoresProgramacaoDiariaDetailComponent,
                ),
              resolve: { onsDadosValoresProgramacaoDiaria: () => of({ id: 9203 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosValoresProgramacaoDiariaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosValoresProgramacaoDiariaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosValoresProgramacaoDiaria on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosValoresProgramacaoDiariaDetailComponent);

      // THEN
      expect(instance.onsDadosValoresProgramacaoDiaria()).toEqual(expect.objectContaining({ id: 9203 }));
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
