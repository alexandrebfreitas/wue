import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCmoSemanalDetailComponent } from './ons-cmo-semanal-detail.component';

describe('OnsCmoSemanal Management Detail Component', () => {
  let comp: OnsCmoSemanalDetailComponent;
  let fixture: ComponentFixture<OnsCmoSemanalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCmoSemanalDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-cmo-semanal-detail.component').then(m => m.OnsCmoSemanalDetailComponent),
              resolve: { onsCmoSemanal: () => of({ id: 18466 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCmoSemanalDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCmoSemanalDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCmoSemanal on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCmoSemanalDetailComponent);

      // THEN
      expect(instance.onsCmoSemanal()).toEqual(expect.objectContaining({ id: 18466 }));
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
