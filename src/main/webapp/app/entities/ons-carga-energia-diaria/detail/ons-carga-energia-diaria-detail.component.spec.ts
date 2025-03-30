import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCargaEnergiaDiariaDetailComponent } from './ons-carga-energia-diaria-detail.component';

describe('OnsCargaEnergiaDiaria Management Detail Component', () => {
  let comp: OnsCargaEnergiaDiariaDetailComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaDiariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaDiariaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-carga-energia-diaria-detail.component').then(m => m.OnsCargaEnergiaDiariaDetailComponent),
              resolve: { onsCargaEnergiaDiaria: () => of({ id: 10395 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCargaEnergiaDiariaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCargaEnergiaDiariaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCargaEnergiaDiaria on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCargaEnergiaDiariaDetailComponent);

      // THEN
      expect(instance.onsCargaEnergiaDiaria()).toEqual(expect.objectContaining({ id: 10395 }));
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
