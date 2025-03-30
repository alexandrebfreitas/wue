import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosGrandezasFluviometricasDetailComponent } from './ons-dados-grandezas-fluviometricas-detail.component';

describe('OnsDadosGrandezasFluviometricas Management Detail Component', () => {
  let comp: OnsDadosGrandezasFluviometricasDetailComponent;
  let fixture: ComponentFixture<OnsDadosGrandezasFluviometricasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosGrandezasFluviometricasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-grandezas-fluviometricas-detail.component').then(m => m.OnsDadosGrandezasFluviometricasDetailComponent),
              resolve: { onsDadosGrandezasFluviometricas: () => of({ id: 29394 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosGrandezasFluviometricasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosGrandezasFluviometricasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosGrandezasFluviometricas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosGrandezasFluviometricasDetailComponent);

      // THEN
      expect(instance.onsDadosGrandezasFluviometricas()).toEqual(expect.objectContaining({ id: 29394 }));
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
