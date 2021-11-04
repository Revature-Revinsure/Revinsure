import { Status } from "./status";

export interface Claim {
    id: number;
    dateOfService: Date;
    dateOfClaim: Date;
    amount: number;
    status: Status;
    description: String;
}
