import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent } from './ons-fator-capacidade-geracao-eolica-e-solar-detail.component';

describe('OnsFatorCapacidadeGeracaoEolicaESolar Management Detail Component', () => {
  let comp: OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent;
  let fixture: ComponentFixture<OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-fator-capacidade-geracao-eolica-e-solar-detail.component').then(
                  m => m.OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent,
                ),
              resolve: { onsFatorCapacidadeGeracaoEolicaESolar: () => of({ id: 30505 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsFatorCapacidadeGeracaoEolicaESolar on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsFatorCapacidadeGeracaoEolicaESolarDetailComponent);

      // THEN
      expect(instance.onsFatorCapacidadeGeracaoEolicaESolar()).toEqual(expect.objectContaining({ id: 30505 }));
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
