import { AuthEvents } from "./events";
import { makeAction, IActionUnion } from "../actions";
import { IUser } from ".";
import { ILoginResponse } from "../../pages/auth/Login";

export const SetLogin = makeAction<AuthEvents.LOGIN, ILoginResponse>(AuthEvents.LOGIN);
export const SetLogout = makeAction<AuthEvents.LOGOUT>(AuthEvents.LOGOUT);

const actions = {
    SetLogin,
    SetLogout,
};

export type IAuthAction = IActionUnion<typeof actions>;
