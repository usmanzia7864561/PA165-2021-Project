import * as React from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router";
import { toast } from "react-toastify";
import FormInput from "../../components/Form/FormInput";
import FormSelect from "../../components/Form/FormSelect";
import courtService from "../../service/court";
import eventService from "../../service/event";
import { ICourt } from "./Courts";

export interface AddEventProps {}

export interface IEventForm {
    name: string;
    description: string;
    eventType: "LESSON" | "TOURNAMENT" | "TENNIS_USER";
    startTime: string;
    endTime: string;
    eventDate: Date;
    courtId: number;
}

const AddEvent: React.FC<AddEventProps> = () => {
    const [courts, setCourts] = React.useState<ICourt[]>([]);
    const { replace } = useHistory();
    const {
        register,
        formState: { errors },
        handleSubmit,
    } = useForm<IEventForm>();

    React.useEffect(() => {
        courtService.fetch<ICourt[]>().then((response) => {
            setCourts(response.data);
        });
    }, []);

    const onAddEvent = (data: IEventForm) => {
        eventService.create(data).then((response) => {
            toast.success("Successfully add new event");
            replace("/");
        });
    };

    return (
        <Form onSubmit={handleSubmit(onAddEvent)}>
            <Row>
                <Col xs={12} md={12}>
                    <FormSelect
                        name="courtId"
                        title="Select Court"
                        error={errors.courtId?.message}
                        register={register("courtId", { required: "Court is required" })}
                    >
                        {courts.map((court) => (
                            <option value={court.id}>{court.name}</option>
                        ))}
                    </FormSelect>
                </Col>
                <Col xs={12} md={6}>
                    <FormInput
                        name="name"
                        title="Event Name"
                        error={errors.name?.message}
                        register={register("name", { required: "Event Name is required" })}
                    />
                </Col>
                <Col xs={12} md={6}>
                    <FormInput
                        name="description"
                        title="Event Description"
                        error={errors.description?.message}
                        register={register("description")}
                    />
                </Col>
                <Col xs={12} md={6}>
                    <FormSelect
                        name="type"
                        title="Event Type"
                        error={errors.eventType?.message}
                        register={register("eventType", { required: "Event Type is required" })}
                    >
                        <option value="LESSON">Lesson</option>
                        <option value="TOURNAMENT">Tournament</option>
                        <option value="TENNIS_USER">Tennis User</option>
                    </FormSelect>
                </Col>
                <Col xs={12} md={6}>
                    <FormInput
                        name="eventDate"
                        title="Event date"
                        type="date"
                        error={errors.eventDate?.message}
                        register={register("eventDate", { required: "Event date is required" })}
                    />
                </Col>
                <Col xs={12} md={6}>
                    <FormInput
                        name="startTime"
                        title="Start Time"
                        type="time"
                        error={errors.startTime?.message}
                        register={register("startTime", { required: "Start time is required" })}
                    />
                </Col>
                <Col xs={12} md={6}>
                    <FormInput
                        name="endTime"
                        type="time"
                        title="Start Time"
                        error={errors.endTime?.message}
                        register={register("endTime", { required: "End time is required" })}
                    />
                </Col>

                <Col xs={12}>
                    <Button variant="success" type="submit">
                        Add
                    </Button>
                </Col>
            </Row>
        </Form>
    );
};

export default AddEvent;
