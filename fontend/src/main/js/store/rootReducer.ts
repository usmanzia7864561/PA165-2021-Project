import { combineReducers } from "redux";
import auth from "./auth";

const reducers = combineReducers({
    auth,
});

export type IAppState = ReturnType<typeof reducers>;

export default reducers;
