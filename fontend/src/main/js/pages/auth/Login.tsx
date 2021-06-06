import React from "react";
import { Form, Button } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { connect } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import { Dispatch } from "redux";
import authService from "../../service/auth";
import { IUser } from "../../store/auth";
import { SetLogin } from "../../store/auth/actions";
import emailValidation from "../../validation/emailValidation";

export interface LoginProps {
    onLogin: typeof SetLogin;
}

export interface ILoginForm {
    email: string;
    password: string;
}

export interface ILoginResponse {
    token: string;
    user: IUser;
}

const Login: React.FC<LoginProps> = ({ ...props }) => {
    const { replace } = useHistory();
    const {
        handleSubmit,
        register,
        formState: { errors },
    } = useForm<ILoginForm>();

    const onLogin = (data: ILoginForm) => {
        authService
            .login<ILoginResponse>(data)
            .then((response) => {
                props.onLogin(response.data);
                replace("/");
            })
            .catch(() => {});
    };

    return (
        <Form method="post" onSubmit={handleSubmit(onLogin)}>
            <h3 className="text-center">Login</h3>

            <Form.Group controlId="email">
                <Form.Label>Email address</Form.Label>
                <Form.Control
                    {...register("email", { required: "email is required", validate: emailValidation })}
                    type="email"
                    name="email"
                    placeholder="Enter email"
                />
                <Form.Text className="text-danger">{errors.email?.message}</Form.Text>
            </Form.Group>

            <Form.Group controlId="password">
                <Form.Label>Password</Form.Label>
                <Form.Control
                    {...register("password", {
                        required: "password is required",
                        minLength: "Min.5 characters",
                        maxLength: "Max.32 characters",
                    })}
                    type="password"
                    name="password"
                    placeholder="******"
                />
                <Form.Text className="text-danger">{errors.password?.message}</Form.Text>
            </Form.Group>

            <div className="text-center">
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                {/* <Link className="btn btn-warning ml-2" to="/auth/register">
                    Register
                </Link> */}
            </div>
        </Form>
    );
};

export default connect(null, { onLogin: SetLogin })(Login);
