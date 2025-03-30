import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsGeracaoUsinaEmBaseHorariaDetailComponent } from './ons-geracao-usina-em-base-horaria-detail.component';

describe('OnsGeracaoUsinaEmBaseHoraria Management Detail Component', () => {
  let comp: OnsGeracaoUsinaEmBaseHorariaDetailComponent;
  let fixture: ComponentFixture<OnsGeracaoUsinaEmBaseHorariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsGeracaoUsinaEmBaseHorariaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-geracao-usina-em-base-horaria-detail.component').then(m => m.OnsGeracaoUsinaEmBaseHorariaDetailComponent),
              resolve: { onsGeracaoUsinaEmBaseHoraria: () => of({ id: 22430 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsGeracaoUsinaEmBaseHorariaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsGeracaoUsinaEmBaseHorariaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsGeracaoUsinaEmBaseHoraria on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsGeracaoUsinaEmBaseHorariaDetailComponent);

      // THEN
      expect(instance.onsGeracaoUsinaEmBaseHoraria()).toEqual(expect.objectContaining({ id: 22430 }));
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
