import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEnaDiarioReservatorioDetailComponent } from './ons-ena-diario-reservatorio-detail.component';

describe('OnsEnaDiarioReservatorio Management Detail Component', () => {
  let comp: OnsEnaDiarioReservatorioDetailComponent;
  let fixture: ComponentFixture<OnsEnaDiarioReservatorioDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEnaDiarioReservatorioDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-ena-diario-reservatorio-detail.component').then(m => m.OnsEnaDiarioReservatorioDetailComponent),
              resolve: { onsEnaDiarioReservatorio: () => of({ id: 28943 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEnaDiarioReservatorioDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEnaDiarioReservatorioDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEnaDiarioReservatorio on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEnaDiarioReservatorioDetailComponent);

      // THEN
      expect(instance.onsEnaDiarioReservatorio()).toEqual(expect.objectContaining({ id: 28943 }));
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
