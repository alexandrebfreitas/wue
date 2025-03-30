import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCmoSemihorarioDetailComponent } from './ons-cmo-semihorario-detail.component';

describe('OnsCmoSemihorario Management Detail Component', () => {
  let comp: OnsCmoSemihorarioDetailComponent;
  let fixture: ComponentFixture<OnsCmoSemihorarioDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCmoSemihorarioDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-cmo-semihorario-detail.component').then(m => m.OnsCmoSemihorarioDetailComponent),
              resolve: { onsCmoSemihorario: () => of({ id: 15398 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCmoSemihorarioDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCmoSemihorarioDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCmoSemihorario on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCmoSemihorarioDetailComponent);

      // THEN
      expect(instance.onsCmoSemihorario()).toEqual(expect.objectContaining({ id: 15398 }));
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
