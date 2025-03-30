import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCargaEnergiaProgramadaDetailComponent } from './ons-carga-energia-programada-detail.component';

describe('OnsCargaEnergiaProgramada Management Detail Component', () => {
  let comp: OnsCargaEnergiaProgramadaDetailComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaProgramadaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaProgramadaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-carga-energia-programada-detail.component').then(m => m.OnsCargaEnergiaProgramadaDetailComponent),
              resolve: { onsCargaEnergiaProgramada: () => of({ id: 16894 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCargaEnergiaProgramadaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCargaEnergiaProgramadaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCargaEnergiaProgramada on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCargaEnergiaProgramadaDetailComponent);

      // THEN
      expect(instance.onsCargaEnergiaProgramada()).toEqual(expect.objectContaining({ id: 16894 }));
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
