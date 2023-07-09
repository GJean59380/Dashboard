import TokenService from "@/services/TokenService";

export default function authHeader() {
  const storedToken = TokenService.getToken();
  return storedToken ? { Authorization: `Bearer ${storedToken}` } : {};
}
