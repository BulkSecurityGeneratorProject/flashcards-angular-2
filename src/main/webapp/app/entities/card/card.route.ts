import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { CardComponent } from './card.component';
import { CardDetailComponent } from './card-detail.component';
import { CardPopupComponent } from './card-dialog.component';
import { CardDeletePopupComponent } from './card-delete-dialog.component';

import { Principal } from '../../shared';


export const cardRoute: Routes = [
  {
    path: 'card',
    component: CardComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'flashcardsApp.card.home.title'
    }
  }, {
    path: 'card/:id',
    component: CardDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'flashcardsApp.card.home.title'
    }
  }
];

export const cardPopupRoute: Routes = [
  {
    path: 'card-new',
    component: CardPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'flashcardsApp.card.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'card/:id/edit',
    component: CardPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'flashcardsApp.card.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'card/:id/delete',
    component: CardDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'flashcardsApp.card.home.title'
    },
    outlet: 'popup'
  }
];
