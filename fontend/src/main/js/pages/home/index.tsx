import * as React from "react";
import { Container } from "react-bootstrap";
import { Switch, RouteProps, Route, Redirect, useRouteMatch } from "react-router-dom";

import MainNavbar from "../../components/MainNavbar";
import Courts from "./Courts";
import AddCourts from "./AddCourt";
import UpdateCourt from "./UpdateCourt";
import Events from "./Events";
import Home from "./Home";
import Participants from "./Participants";
import Users from "./Users";
import UpdateUser from "./UpdateUser";
import AddEvent from "./AddEvent";
import AddParticipant from "./AddParticipant";

export interface MainProps {}

interface IProtectedRouteProps extends RouteProps {
    isLoggedIn: boolean;
    component: any;
}

export const ProtectedRoute: React.FC<IProtectedRouteProps> = ({ isLoggedIn, component: Component, ...rest }) => {
    return (
        <Route {...rest} render={(props) => (isLoggedIn ? <Component {...props} /> : <Redirect to="/auth/login" />)} />
    );
};

const Main: React.FC<MainProps> = () => {
    const { path } = useRouteMatch();

    return (
        <React.Fragment>
            <MainNavbar />
            <Container className="mt-4">
                <Switch>
                    <Route path={`${path}participants/:eventId/add`} component={AddParticipant} />
                    <Route path={`${path}participants/:eventId`} component={Participants} />
                    <Route path={`${path}events/add`} component={AddEvent} />
                    <Route path={`${path}events/:courtId`} component={Events} />
                    <Route path={`${path}courts/add`} component={AddCourts} />
                    <Route path={`${path}court/update/:courtId`} component={UpdateCourt} />
                    <Route path={`${path}courts`} component={Courts} />
                    <Route path={`${path}users/add`} component={Users} />
                    <Route path={`${path}users`} component={Users} />
                    <Route path={`${path}user/update/:userId`} component={UpdateUser} />
                    <Route path={path} component={Home} />
                </Switch>
            </Container>
        </React.Fragment>
    );
};

export default Main;
