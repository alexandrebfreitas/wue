import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent } from './ons-ear-diario-ree-reservatorio-equivalente-energia-detail.component';

describe('OnsEarDiarioReeReservatorioEquivalenteEnergia Management Detail Component', () => {
  let comp: OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent;
  let fixture: ComponentFixture<OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-ear-diario-ree-reservatorio-equivalente-energia-detail.component').then(
                  m => m.OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent,
                ),
              resolve: { onsEarDiarioReeReservatorioEquivalenteEnergia: () => of({ id: 21274 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEarDiarioReeReservatorioEquivalenteEnergia on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEarDiarioReeReservatorioEquivalenteEnergiaDetailComponent);

      // THEN
      expect(instance.onsEarDiarioReeReservatorioEquivalenteEnergia()).toEqual(expect.objectContaining({ id: 21274 }));
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
