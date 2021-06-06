import React, { useState, useEffect } from "react";

import FormInput from "../../components/Form/FormInput";

import { Form, Row, Col, Button } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { Link, useHistory, useParams } from "react-router-dom";
import { IUser } from "../../store/auth";
import { toast } from "react-toastify";
import userService from "../../service/user";
import FormSelect from "../../components/Form/FormSelect";

export interface IUserForm {
    name: string;
    type: "MANAGER" | "TENNIS_USER";
    password: string;
}

const UpdateCourt: React.FC = () => {
    const { userId } = useParams<{ userId: string }>();
    const { replace } = useHistory();
    const [user, setUser] = useState<IUser>();
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        userService.fetch<IUser>(parseInt(userId)).then((response) => {
            if (response.data == null) {
                toast.error("User No found");
                replace("/users/");
            }
            setUser(response.data);

            setLoading(false);
        });
    }, []);
    const {
        register,
        formState: { errors },
        handleSubmit,
    } = useForm<IUserForm>();

    const onUpdateCourt = (data: IUserForm) => {
        userService.update(data, parseInt(userId)).then((response) => {
            toast.success("Successfully update the user");
            replace("/users/");
        });
    };
    if (loading) {
        return <div>loading...</div>;
    }
    return (
        <Form onSubmit={handleSubmit(onUpdateCourt)}>
            <Row>
                <Col xs={12} md={12}>
                    <FormInput
                        name="name"
                        title="Name"
                        value={user?.name}
                        error={errors.name?.message}
                        register={register("name", { required: "Name is required" })}
                    />
                </Col>

                <Col xs={12} md={12}>
                    <FormSelect
                        name="type"
                        title="Type"
                        value={user?.type}
                        error={errors.type?.message}
                        register={register("type", { required: "type is required" })}
                    >
                        <option value="MANAGER">Manager</option>
                        <option value="TENNIS_USER">Tennis User</option>
                    </FormSelect>
                </Col>

                <Col xs={12} md={12}>
                    <FormInput
                        name="password"
                        title="Password"
                        error={errors.password?.message}
                        register={register("password", { required: "Password is required" })}
                    />
                </Col>

                <Col xs={12}>
                    <div className="text-center">
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                        <Link className="btn btn-info ml-2" to="/users/">
                            Back
                        </Link>
                    </div>
                </Col>
            </Row>
        </Form>
    );
};

export default UpdateCourt;
