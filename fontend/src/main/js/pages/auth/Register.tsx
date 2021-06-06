import * as React from "react";
import { Button, Form } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { connect } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import FormInput from "../../components/Form/FormInput";
import authService from "../../service/auth";

import { SetLogin } from "../../store/auth/actions";
import emailValidation from "../../validation/emailValidation";

export interface RegisterProps {
    onRegister: typeof SetLogin;
}

export interface IRegisterForm {
    name: string;
    email: string;
    password: string;
    confirm_password: string;
}

const Register: React.FC<RegisterProps> = ({ ...props }) => {
    const { replace } = useHistory();
    const {
        handleSubmit,
        register,
        watch,
        formState: { errors },
    } = useForm<IRegisterForm>();

    const password = watch("password");

    const onRegister = (data: IRegisterForm) => {
        authService.register(data);
        replace("/");
    };

    return (
        <Form onSubmit={handleSubmit(onRegister)}>
            <h3 className="text-center">Register</h3>

            <Form.Group controlId="name">
                <Form.Label>Name</Form.Label>
                <Form.Control
                    {...register("name", {
                        required: "name is required",
                        minLength: "Min.3 characters",
                        maxLength: "Max.120 characters",
                    })}
                    type="text"
                    name="name"
                    placeholder="Enter name"
                />
                <Form.Text className="text-danger">{errors.name?.message}</Form.Text>
            </Form.Group>

            <FormInput
                name="email"
                register={register("email", {
                    required: "email is required",
                    validate: emailValidation,
                })}
                error={errors.email?.message}
            />

            <FormInput
                name="password"
                register={register("password", {
                    required: "password is required",
                    minLength: "Min.5 characters",
                    maxLength: "Max.32 characters",
                })}
                error={errors.password?.message}
            />

            <Form.Group controlId="confirm-password">
                <Form.Label>Confirm Password</Form.Label>
                <Form.Control
                    {...register("confirm_password", {
                        validate: (value) => value === password || "Passwords does not match",
                    })}
                    type="password"
                    name="confirm_password"
                    placeholder="******"
                />
                <Form.Text className="text-danger">{errors.confirm_password?.message}</Form.Text>
            </Form.Group>

            <div className="text-center">
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Link className="btn btn-warning ml-2" to="/auth">
                    Login
                </Link>
            </div>
        </Form>
    );
};

export default connect(null, { onRegister: SetLogin })(Register);
