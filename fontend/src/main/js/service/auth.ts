import { ILoginForm } from "../pages/auth/Login";
import { IRegisterForm } from "../pages/auth/Register";
import service from "./instance";

const authService = {
    login: <T>(data: ILoginForm) => service.post<T>("/auth/login", data),
    register: <T>(data: IRegisterForm) => service.post<T>("/auth/register", data),
    refreshToken: <T>() => service.get<T>("/auth/refresh"),
};

export default authService;
