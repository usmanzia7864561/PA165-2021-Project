import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import { toast } from "react-toastify";
import store from "../store";

const service = axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
        "access-control-allow-origin": "*",
        Accept: "application/json",
        "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE",
    },
});

service.interceptors.request.use((config: AxiosRequestConfig) => {
    if (store.getState().auth.token) {
        config.headers["Authorization"] = `Bearer ${store.getState().auth.token}`;
    }
    return config;
});

service.interceptors.response.use(
    (response: AxiosResponse) => {
        return response;
    },
    (error: AxiosError) => {
        if (error.response?.status === 403) {
            toast("This operation is forbidden.", {
                type: "error",
            });
        } else if (error.response?.data.message) {
            toast(error.response?.data.message, {
                type: "error",
            });
        }

        return error;
    }
);

export default service;
