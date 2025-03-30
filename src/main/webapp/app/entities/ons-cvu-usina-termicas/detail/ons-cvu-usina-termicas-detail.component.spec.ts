import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCvuUsinaTermicasDetailComponent } from './ons-cvu-usina-termicas-detail.component';

describe('OnsCvuUsinaTermicas Management Detail Component', () => {
  let comp: OnsCvuUsinaTermicasDetailComponent;
  let fixture: ComponentFixture<OnsCvuUsinaTermicasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCvuUsinaTermicasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-cvu-usina-termicas-detail.component').then(m => m.OnsCvuUsinaTermicasDetailComponent),
              resolve: { onsCvuUsinaTermicas: () => of({ id: 30136 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCvuUsinaTermicasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCvuUsinaTermicasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCvuUsinaTermicas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCvuUsinaTermicasDetailComponent);

      // THEN
      expect(instance.onsCvuUsinaTermicas()).toEqual(expect.objectContaining({ id: 30136 }));
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
