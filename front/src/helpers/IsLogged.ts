import TokenService from "@/services/TokenService";

export default function isLogged(): boolean {
  return !!TokenService.getToken();
}
