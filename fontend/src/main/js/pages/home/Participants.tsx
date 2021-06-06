import * as React from "react";
import { Button } from "react-bootstrap";
import { useParams } from "react-router-dom";

import TableWrapper from "../../components/TableWrapper";
import participantService from "../../service/participant";

export interface ParticipantsProps {}

export interface IParticipant {
    id: number;
    name: string;
}

const Participants: React.FC<ParticipantsProps> = () => {
    const { eventId } = useParams<{ eventId: string }>();
    const [participants, setParticipants] = React.useState<IParticipant[]>([]);

    React.useEffect(() => {
        participantService.fetchByEvent<IParticipant[]>(parseInt(eventId)).then((response) => {
            setParticipants(response.data);
        });
    }, []);

    const onDeleteParticipant = (participantId: number) => () => {
        participantService.delete(participantId).finally(() => {
            setParticipants(participants.filter((participant) => participant.id !== participantId));
        });
    };

    return (
        <TableWrapper title="Participants" addLink={`/participants/${eventId}/add`} addText="Add Participants">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {participants.map((participant) => (
                    <tr key={participant.id}>
                        <td>{participant.id}</td>
                        <td>{participant.name}</td>
                        <td className="text-right">
                            <Button size="sm" onClick={onDeleteParticipant(participant.id)} variant="danger">
                                Delete
                            </Button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </TableWrapper>
    );
};

export default Participants;
