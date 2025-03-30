jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsBalancoEnergiaNosSubsistemasService } from '../service/ons-balanco-energia-nos-subsistemas.service';

import { OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent } from './ons-balanco-energia-nos-subsistemas-delete-dialog.component';

describe('OnsBalancoEnergiaNosSubsistemas Management Delete Component', () => {
  let comp: OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent;
  let fixture: ComponentFixture<OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent>;
  let service: OnsBalancoEnergiaNosSubsistemasService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsBalancoEnergiaNosSubsistemasDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsBalancoEnergiaNosSubsistemasService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
