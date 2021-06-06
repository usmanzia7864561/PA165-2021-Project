import * as React from "react";
import { Navbar, Container, Nav } from "react-bootstrap";
import { connect } from "react-redux";
import { Link, NavLink } from "react-router-dom";

import { SetLogout } from "../store/auth/actions";

export interface MainNavbarProps {
    onLogout: typeof SetLogout;
}

const MainNavbar: React.FC<MainNavbarProps> = ({ ...props }) => {
    const onLogout = () => props.onLogout(undefined);

    return (
        <Navbar bg="light">
            <Container>
                <Navbar.Brand as={Link} to="/">Tennis Court Manager</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ml-auto">
                        <Nav.Link as={NavLink} exact activeClassName="active" to="/">
                            Home
                        </Nav.Link>
                        <Nav.Link as={NavLink} activeClassName="active" to="/users">
                            Users
                        </Nav.Link>

                        <Nav.Link as={NavLink} activeClassName="active" to="/courts">
                            Courts
                        </Nav.Link>

                        {/* <Nav.Link as={NavLink} activeClassName="active" to="/profile">
                            Profile
                        </Nav.Link> */}

                        <Nav.Link onClick={onLogout}>Logout</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default connect(null, { onLogout: SetLogout })(MainNavbar);
