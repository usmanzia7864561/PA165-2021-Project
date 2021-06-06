import { AuthEvents } from "./auth/events";

type Events = AuthEvents;

export const makeAction = <T extends Events, P = undefined>(type: T) => (payload: P) => {
    return {
        type,
        payload,
    };
};

interface IStringMap<T> {
    [key: string]: T;
}
type IAnyFunction = (...args: any[]) => any;
export type IActionUnion<A extends IStringMap<IAnyFunction>> = ReturnType<A[keyof A]>;
