import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subject, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { sampleWithRequiredData } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.test-samples';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.component';
import SpyInstance = jest.SpyInstance;

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 Management Component', () => {
  let comp: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component;
  let fixture: ComponentFixture<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component>;
  let service: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component],
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: {
              queryParams: {},
              queryParamMap: jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            },
          },
        },
      ],
    })
      .overrideTemplate(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    jest
      .spyOn(service, 'query')
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 6420 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=1&size=20>; rel="next"',
            }),
          }),
        ),
      )
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 31479 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=0&size=20>; rel="prev",<http://localhost/api/foo?page=2&size=20>; rel="next"',
            }),
          }),
        ),
      );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s()[0]).toEqual(expect.objectContaining({ id: 6420 }));
  });

  describe('trackId', () => {
    it('Should forward to onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service', () => {
      const entity = { id: 6420 };
      jest.spyOn(service, 'getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier');
      const id = comp.trackId(entity);
      expect(service.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // WHEN
    comp.navigateToWithComponentValues({ predicate: 'non-existing-column', order: 'asc' });

    // THEN
    expect(routerNavigateSpy).toHaveBeenLastCalledWith(
      expect.anything(),
      expect.objectContaining({
        queryParams: expect.objectContaining({
          sort: ['non-existing-column,asc'],
        }),
      }),
    );
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
  });

  describe('delete', () => {
    let ngbModal: NgbModal;
    let deleteModalMock: any;

    beforeEach(() => {
      deleteModalMock = { componentInstance: {}, closed: new Subject() };
      // NgbModal is not a singleton using TestBed.inject.
      // ngbModal = TestBed.inject(NgbModal);
      ngbModal = (comp as any).modalService;
      jest.spyOn(ngbModal, 'open').mockReturnValue(deleteModalMock);
    });

    it('on confirm should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next('deleted');
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).toHaveBeenCalled();
      }),
    ));

    it('on dismiss should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next();
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).not.toHaveBeenCalled();
      }),
    ));
  });
});
