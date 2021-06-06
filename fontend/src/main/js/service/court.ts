import service from "./instance";

const courtService = {
    fetch: <T>(id?: number) => service.get<T>(`/court/${id || ""}`),
    create: <T>(data: any) => service.post<T>("/court/add", data),
    update: <T>(data: any , id:number) => service.put<T>(`/court/${id}`, data),
    delete: <T>(id: number) => service.delete<T>(`/court/${id}`),
};

export default courtService;
