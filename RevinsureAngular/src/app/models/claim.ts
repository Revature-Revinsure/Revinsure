import { Status } from "./status";

export interface Claim {
    id: number;
    dateOfService: Date | number | null;
    dateOfClaim: Date | number;
    amount: number;
    status: Status;
    description: String;
}
