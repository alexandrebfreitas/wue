import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent } from './ons-dados-hidraulicos-reservatorio-base-diaria-detail.component';

describe('OnsDadosHidraulicosReservatorioBaseDiaria Management Detail Component', () => {
  let comp: OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent;
  let fixture: ComponentFixture<OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-hidraulicos-reservatorio-base-diaria-detail.component').then(
                  m => m.OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent,
                ),
              resolve: { onsDadosHidraulicosReservatorioBaseDiaria: () => of({ id: 29132 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosHidraulicosReservatorioBaseDiaria on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosHidraulicosReservatorioBaseDiariaDetailComponent);

      // THEN
      expect(instance.onsDadosHidraulicosReservatorioBaseDiaria()).toEqual(expect.objectContaining({ id: 29132 }));
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
