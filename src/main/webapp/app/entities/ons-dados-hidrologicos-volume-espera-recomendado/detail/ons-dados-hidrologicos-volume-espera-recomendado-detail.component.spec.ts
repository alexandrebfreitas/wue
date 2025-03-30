import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent } from './ons-dados-hidrologicos-volume-espera-recomendado-detail.component';

describe('OnsDadosHidrologicosVolumeEsperaRecomendado Management Detail Component', () => {
  let comp: OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent;
  let fixture: ComponentFixture<OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-hidrologicos-volume-espera-recomendado-detail.component').then(
                  m => m.OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent,
                ),
              resolve: { onsDadosHidrologicosVolumeEsperaRecomendado: () => of({ id: 16 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosHidrologicosVolumeEsperaRecomendado on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosHidrologicosVolumeEsperaRecomendadoDetailComponent);

      // THEN
      expect(instance.onsDadosHidrologicosVolumeEsperaRecomendado()).toEqual(expect.objectContaining({ id: 16 }));
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
