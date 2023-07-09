// @ts-ignore
import http from "@/services/HttpInfo";

class ApiService {
  // @ts-ignore
  public static async getAll(request: string): Promise<any> {
    return http.get(request).then(({ data }) => {
      return data;
    });
  }

  // @ts-ignore
  public static async get(request: string, id: number): Promise<any> {
    return http.get(`${request}/${id}`).then(({ data }) => {
      return data;
    });
  }

  // @ts-ignore
  public static async create(request: string, data: any): Promise<any> {
    return http.post(request, data).then(({ data }) => {
      return data;
    });
  }

  // @ts-ignore
  public static async update(
    request: string,
    data: any
  ): Promise<any> {
    return http.patch(`${request}/`, data).then(({ data }) => {
      return data;
    });
  }
}

export default ApiService;
