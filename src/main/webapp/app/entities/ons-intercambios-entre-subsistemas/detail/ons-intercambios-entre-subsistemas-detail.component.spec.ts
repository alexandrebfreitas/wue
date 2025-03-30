import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIntercambiosEntreSubsistemasDetailComponent } from './ons-intercambios-entre-subsistemas-detail.component';

describe('OnsIntercambiosEntreSubsistemas Management Detail Component', () => {
  let comp: OnsIntercambiosEntreSubsistemasDetailComponent;
  let fixture: ComponentFixture<OnsIntercambiosEntreSubsistemasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIntercambiosEntreSubsistemasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-intercambios-entre-subsistemas-detail.component').then(m => m.OnsIntercambiosEntreSubsistemasDetailComponent),
              resolve: { onsIntercambiosEntreSubsistemas: () => of({ id: 25404 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIntercambiosEntreSubsistemasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIntercambiosEntreSubsistemasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIntercambiosEntreSubsistemas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIntercambiosEntreSubsistemasDetailComponent);

      // THEN
      expect(instance.onsIntercambiosEntreSubsistemas()).toEqual(expect.objectContaining({ id: 25404 }));
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
