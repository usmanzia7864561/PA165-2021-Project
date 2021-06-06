import * as React from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import FormInput from "../../components/Form/FormInput";
import FormSelect from "../../components/Form/FormSelect";
import { Link, useHistory } from "react-router-dom";
import courtService from "../../service/court";

import { toast } from 'react-toastify';

export interface AddCourtProps {}

export interface ICourtForm {
    name: string;
    type: string;
    location:string;
    isAvailable:boolean;
}

const AddCourt: React.FC<AddCourtProps> = () => {
    const { push } = useHistory();
    const {
        register,
        formState: { errors },
        handleSubmit,
    } = useForm<ICourtForm>();

    const onAddCourt = (data:ICourtForm) => {
        data.isAvailable = true;
        courtService.create(data).then((response) => {
            toast.success("Successfully add new court")
            push('courts/');

        });;
    };

    return (
        <Form onSubmit={handleSubmit(onAddCourt)}>
            <Row>
                <Col xs={12} md={6}>
                    <FormInput
                        name="name"
                        title="Court Name"
                        error={errors.name?.message}
                        register={register("name", { required: "Court Name is required" })}
                    />
                </Col>
                <Col xs={12} md={6}>
                <FormInput
                        name="location"
                        title="Location"
                        error={errors.location?.message}
                        register={register("location", { required: "Location is required" })}
                    />
                </Col>
                <Col xs={12} md={6}>
                    <FormSelect
                        name="type"
                        title="Court Type"
                        error={errors.type?.message}
                        register={register("type", { required: "Court Type is required" })}
                    >
                        <option value="Grass">Grass</option>
                        <option value="Sand">Sand</option>
                        <option value="Clay">Clay</option>
                    </FormSelect>
                </Col>
                
                <Col xs={12}>
                   <div className="text-center">
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                        <Link className="btn btn-info ml-2" to="/courts/">
                            Back
                        </Link>
                 </div>
                </Col>
            </Row>
        </Form>
    );
};

export default AddCourt;
