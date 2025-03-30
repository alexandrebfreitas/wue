import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCargaEnergiaVerificadaDetailComponent } from './ons-carga-energia-verificada-detail.component';

describe('OnsCargaEnergiaVerificada Management Detail Component', () => {
  let comp: OnsCargaEnergiaVerificadaDetailComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaVerificadaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaVerificadaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-carga-energia-verificada-detail.component').then(m => m.OnsCargaEnergiaVerificadaDetailComponent),
              resolve: { onsCargaEnergiaVerificada: () => of({ id: 29329 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCargaEnergiaVerificadaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCargaEnergiaVerificadaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCargaEnergiaVerificada on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCargaEnergiaVerificadaDetailComponent);

      // THEN
      expect(instance.onsCargaEnergiaVerificada()).toEqual(expect.objectContaining({ id: 29329 }));
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
