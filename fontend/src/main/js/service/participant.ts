import { IParticipantForm } from "../pages/home/AddParticipant";
import service from "./instance";

const participantService = {
    fetchByEvent: <T>(courtId: number) => service.get<T>(`/participant/event/${courtId}`),
    fetch: <T>(id?: number) => service.get<T>(`/participant/${id}`),
    create: <T>(eventId: number, data: IParticipantForm) => service.post<T>(`/participant/${eventId}/add`, data),
    delete: <T>(id: number) => service.delete<T>(`/participant/${id}`),
};

export default participantService;
