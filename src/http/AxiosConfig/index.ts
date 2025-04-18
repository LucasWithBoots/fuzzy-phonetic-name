import axios from "axios";

const BACK_URL = "http://localhost:8080/api/v1/";

const http = axios.create({
  baseURL: BACK_URL,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export { http };
