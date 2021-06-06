import { IAuthAction } from "./actions";
import { AuthEvents } from "./events";

export interface IUser {
    id: number;
    name: string;
    email: string;
    type: "MANAGER" | "TENNIS_USER";
    createdAt: Date;
}

interface IAuthState {
    isLoggedIn: boolean;
    token?: string;
    user?: IUser;
}

const initial: IAuthState = {
    isLoggedIn: false,
};

const auth = (state: IAuthState = initial, action: IAuthAction): IAuthState => {
    switch (action.type) {
        case AuthEvents.LOGIN:
            return { ...state, isLoggedIn: true, user: action.payload.user, token: action.payload.token };
        case AuthEvents.LOGOUT:
            return { isLoggedIn: false };
        default:
            return { ...state };
    }
};

export default auth;
