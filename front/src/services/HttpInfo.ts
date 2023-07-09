// @ts-ignore
import axios, { AxiosInstance } from "axios";
import AuthHeader from "@/helpers/AuthHeader";

const apiClient: AxiosInstance = axios.create({
  baseURL: `http://localhost:8080`,
  headers: AuthHeader(),
});

export default apiClient;
