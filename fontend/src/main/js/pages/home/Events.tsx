import * as React from "react";
import { Button } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

import TableWrapper from "../../components/TableWrapper";
import { dateFormat } from "../../helper/dataHelper";
import eventService from "../../service/event";
import { IUser } from "../../store/auth";
import { ICourt } from "./Courts";
import { IParticipant } from "./Participants";

export interface EventsProps {}

export interface IEvent {
    id: number;
    name: string;
    description: string;
    startTime: Date;
    endTime: Date;
    eventDate: Date;
    eventType: "TENNIS_USER" | "LESSON" | "TOURNAMENT";
    user: IUser;
    court: ICourt;
    participants: IParticipant;
}

const Events: React.FC<EventsProps> = () => {
    const { courtId } = useParams<{ courtId: string }>();
    const [events, setEvents] = React.useState<IEvent[]>([]);

    React.useEffect(() => {
        eventService.fetchByCourt<IEvent[]>(parseInt(courtId)).then((response) => {
            setEvents(response.data);
        });
    }, []);

    const onDeleteEvent = (eventId: number) => () => {
        eventService.delete(eventId).then(() => {
            setEvents(events.filter((event) => event.id !== eventId));
        });
    };

    return (
        <TableWrapper title="Events" addLink="/events/add" addText="Add Events">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {events.map((event) => (
                    <tr key={event.id}>
                        <td>{event.id}</td>
                        <td>{event.name}</td>
                        <td>{event.description}</td>
                        <td>{event.eventType}</td>
                        <td>{dateFormat(event.eventDate)}</td>
                        <td>
                            {event.startTime} - {event.endTime}
                        </td>
                        <td className="text-right">
                            <Button
                                as={Link}
                                to={`/participants/${event.id}`}
                                size="sm"
                                className="mr-1"
                                variant="warning"
                            >
                                Participants
                            </Button>
                            {/* <Button size="sm" className="mr-1" variant="primary">
                                Edit
                            </Button> */}
                            <Button size="sm" onClick={onDeleteEvent(event.id)} variant="danger">
                                Delete
                            </Button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </TableWrapper>
    );
};

export default Events;
