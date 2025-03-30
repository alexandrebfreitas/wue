import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEarDiarioReservatorioDetailComponent } from './ons-ear-diario-reservatorio-detail.component';

describe('OnsEarDiarioReservatorio Management Detail Component', () => {
  let comp: OnsEarDiarioReservatorioDetailComponent;
  let fixture: ComponentFixture<OnsEarDiarioReservatorioDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEarDiarioReservatorioDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-ear-diario-reservatorio-detail.component').then(m => m.OnsEarDiarioReservatorioDetailComponent),
              resolve: { onsEarDiarioReservatorio: () => of({ id: 29357 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEarDiarioReservatorioDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEarDiarioReservatorioDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEarDiarioReservatorio on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEarDiarioReservatorioDetailComponent);

      // THEN
      expect(instance.onsEarDiarioReservatorio()).toEqual(expect.objectContaining({ id: 29357 }));
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
