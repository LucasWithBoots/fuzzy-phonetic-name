import { IPhoneticResponse, IPhoneticSend } from "@/interfaces/IPhonetic";
import { http } from "../AxiosConfig";

const getSimilar = async (arg: IPhoneticSend) => {
  try {
    const response = await http.get<IPhoneticResponse[]>(`/phonetics`, {
      params: {
        name: arg.name,
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching phonetics:", error);
    throw error;
  }
};

const postPhonetic = async (arg: IPhoneticSend) => {
  try {
    const response = await http.post<IPhoneticResponse>(`/phonetics`, arg);
    return response.data;
  } catch (error) {
    console.error("Error posting phonetic:", error);
    throw error;
  }
};

export { getSimilar, postPhonetic };
