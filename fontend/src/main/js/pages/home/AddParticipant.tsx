import * as React from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { useHistory, useParams } from "react-router";
import FormInput from "../../components/Form/FormInput";
import FormSelect from "../../components/Form/FormSelect";
import participantService from "../../service/participant";

export interface AddParticipantProps {}

export interface IParticipantForm {
    name: string;
}

const AddParticipant: React.FC<AddParticipantProps> = () => {
    const { eventId } = useParams<{ eventId: string }>();
    const { replace } = useHistory();
    const {
        register,
        formState: { errors },
        handleSubmit,
    } = useForm<IParticipantForm>();

    const onAddParticipant = (data: IParticipantForm) => {
        participantService.create(parseInt(eventId), data).then(() => replace("/"));
    };

    return (
        <Form onSubmit={handleSubmit(onAddParticipant)}>
            <Row>
                <Col xs={12} md={12}>
                    <FormInput
                        title="Participant Name"
                        name="name"
                        error={errors.name?.message}
                        register={register("name", { required: "name is required" })}
                    />
                </Col>
                <Col xs={12} md={12}>
                    <Button variant="success" type="submit">
                        Add
                    </Button>
                </Col>
            </Row>
        </Form>
    );
};

export default AddParticipant;
