import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent } from './ons-dados-hidraulicos-reservatorio-base-horaria-detail.component';

describe('OnsDadosHidraulicosReservatorioBaseHoraria Management Detail Component', () => {
  let comp: OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent;
  let fixture: ComponentFixture<OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-hidraulicos-reservatorio-base-horaria-detail.component').then(
                  m => m.OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent,
                ),
              resolve: { onsDadosHidraulicosReservatorioBaseHoraria: () => of({ id: 14970 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosHidraulicosReservatorioBaseHoraria on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosHidraulicosReservatorioBaseHorariaDetailComponent);

      // THEN
      expect(instance.onsDadosHidraulicosReservatorioBaseHoraria()).toEqual(expect.objectContaining({ id: 14970 }));
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
