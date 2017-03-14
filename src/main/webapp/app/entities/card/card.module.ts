import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FlashcardsSharedModule } from '../../shared';

import {
    CardService,
    CardPopupService,
    CardComponent,
    CardDetailComponent,
    CardDialogComponent,
    CardPopupComponent,
    CardDeletePopupComponent,
    CardDeleteDialogComponent,
    cardRoute,
    cardPopupRoute,
} from './';

let ENTITY_STATES = [
    ...cardRoute,
    ...cardPopupRoute,
];

@NgModule({
    imports: [
        FlashcardsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CardComponent,
        CardDetailComponent,
        CardDialogComponent,
        CardDeleteDialogComponent,
        CardPopupComponent,
        CardDeletePopupComponent,
    ],
    entryComponents: [
        CardComponent,
        CardDialogComponent,
        CardPopupComponent,
        CardDeleteDialogComponent,
        CardDeletePopupComponent,
    ],
    providers: [
        CardService,
        CardPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FlashcardsCardModule {}
