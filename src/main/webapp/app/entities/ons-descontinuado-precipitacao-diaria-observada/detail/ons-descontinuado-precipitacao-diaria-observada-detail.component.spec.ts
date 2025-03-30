import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent } from './ons-descontinuado-precipitacao-diaria-observada-detail.component';

describe('OnsDescontinuadoPrecipitacaoDiariaObservada Management Detail Component', () => {
  let comp: OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent;
  let fixture: ComponentFixture<OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-descontinuado-precipitacao-diaria-observada-detail.component').then(
                  m => m.OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent,
                ),
              resolve: { onsDescontinuadoPrecipitacaoDiariaObservada: () => of({ id: 5142 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDescontinuadoPrecipitacaoDiariaObservada on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDescontinuadoPrecipitacaoDiariaObservadaDetailComponent);

      // THEN
      expect(instance.onsDescontinuadoPrecipitacaoDiariaObservada()).toEqual(expect.objectContaining({ id: 5142 }));
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
