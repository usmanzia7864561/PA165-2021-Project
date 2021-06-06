import * as React from "react";
import { Col, Container, Row } from "react-bootstrap";
import { useRouteMatch, Switch, Route } from "react-router-dom";
import Login from "./Login";
import Register from "./Register";

export interface AuthProps {}

const Auth: React.FC<AuthProps> = () => {
    const { path } = useRouteMatch();

    return (
        <Container>
            <Row className="justify-content-center align-items-center h-100v">
                <Col xs={12} md={5}>
                    <Switch>
                        <Route path={`${path}/register`} component={Register} />
                        <Route path={path} component={Login} />
                    </Switch>
                </Col>
            </Row>
        </Container>
    );
};

export default Auth;
