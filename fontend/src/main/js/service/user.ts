import service from "./instance";

const userService = {
    fetch: <T>(id: number) => service.get<T>(`/user/${id}`),
    update: <T>(data:any , id: number) => service.put<T>(`/user/${id}`,data),
    all: <T>() => service.get<T>("/user/"),
    delete: <T>(id: number) => service.delete<T>(`/user/${id}`),
};

export default userService;
