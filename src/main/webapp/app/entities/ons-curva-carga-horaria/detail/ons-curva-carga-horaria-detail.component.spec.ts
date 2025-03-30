import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCurvaCargaHorariaDetailComponent } from './ons-curva-carga-horaria-detail.component';

describe('OnsCurvaCargaHoraria Management Detail Component', () => {
  let comp: OnsCurvaCargaHorariaDetailComponent;
  let fixture: ComponentFixture<OnsCurvaCargaHorariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCurvaCargaHorariaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-curva-carga-horaria-detail.component').then(m => m.OnsCurvaCargaHorariaDetailComponent),
              resolve: { onsCurvaCargaHoraria: () => of({ id: 21772 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCurvaCargaHorariaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCurvaCargaHorariaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCurvaCargaHoraria on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCurvaCargaHorariaDetailComponent);

      // THEN
      expect(instance.onsCurvaCargaHoraria()).toEqual(expect.objectContaining({ id: 21772 }));
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
