export class Card {
    constructor(
        public id?: number,
        public front?: string,
        public back?: string,
        public known?: boolean,
    ) {
        this.known = false;
    }
}
